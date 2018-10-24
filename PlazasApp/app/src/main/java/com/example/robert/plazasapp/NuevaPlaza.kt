package com.example.robert.plazasapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import com.google.zxing.integration.android.IntentIntegrator
import org.json.JSONException
import org.json.JSONObject

class NuevaPlaza : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etDescripcion: EditText
    private lateinit var etImagen: EditText
    private lateinit var etTelefono: EditText
    private lateinit var etLatitud: EditText
    private lateinit var etLongitud: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var btnQr: Button
    private lateinit var qrResult:TextView

    //QR Code Scanner Object
    private var qrScanIntegrator: IntentIntegrator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_plaza)

        etNombre = findViewById(R.id.etNombre)
        etDescripcion = findViewById(R.id.etDescripcion)
        etImagen = findViewById(R.id.etImagen)
        etTelefono = findViewById(R.id.etTelefono)
        etLatitud = findViewById(R.id.etLatitud)
        etLongitud = findViewById(R.id.etLongitud)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnQr = findViewById(R.id.btnQr)

        qrScanIntegrator = IntentIntegrator(this)
        qrScanIntegrator?.setOrientationLocked(false)

        btnRegistrar.setOnClickListener {
            registrarPlaza()
        }

        btnQr.setOnClickListener {
            scanQR()
        }

    }

    private fun scanQR() {
        qrScanIntegrator?.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result != null) {
            // If QRCode has no data.
            if (result.contents == null) {
                Toast.makeText(this,"Result Not Found", Toast.LENGTH_LONG).show()
            } else {

                try {
                    // Converting the data to json format
                    val obj = JSONObject(result.contents)

                    // Show values in UI.
                    etNombre.setText(obj.getString("nombre"))
                    etDescripcion.setText(obj.getString("descripcion"))
                    etImagen.setText(obj.getString("imagen"))
                    etTelefono.setText(obj.getString("telefono"))
                    etLatitud.setText(obj.getString("latitud"))
                    etLongitud.setText(obj.getString("longitud"))

                } catch (e: JSONException) {
                    e.printStackTrace()
                    // Data not in the expected format. So, whole object as toast message.
                    Toast.makeText(this, result.contents, Toast.LENGTH_LONG).show()
                }

            }
        } else {
            Toast.makeText(this,"Null!", Toast.LENGTH_LONG).show()
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun registrarPlaza() {

        val url = "https://plazasapp.herokuapp.com/api/plaza"

        if(isNetworkConnected() && etNombre.text.toString()!="" && etDescripcion.text.toString()!="" && etImagen.text.toString()!="" && etTelefono.text.toString()!="" && etLatitud.text.toString()!="" && etLongitud.text.toString()!=""){

            val plaza = PostPlaza(
                    nombre = etNombre.text.toString(),
                    descripcion = etDescripcion.text.toString(),
                    imagen = etImagen.text.toString(),
                    telefono = etTelefono.text.toString(),
                    latitud = etLatitud.text.toString(),
                    longitud = etLongitud.text.toString()
            )
            val plazaJson = Gson().toJson(plaza)

            try {
                Fuel.post(url).header("Content-Type" to "application/json").body(plazaJson.toString()).response { request, response, result ->

                    Toast.makeText(this,"Plaza registrada!",Toast.LENGTH_SHORT).show()
                    onBackPressed()

                }
            } catch (e: Exception) {
                Toast.makeText(this,"Error! ${e.message}",Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this,"Hay campos vacios",Toast.LENGTH_SHORT).show()
        }

    }

    //Function to check internet connectivity
    fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager //1
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }




}
