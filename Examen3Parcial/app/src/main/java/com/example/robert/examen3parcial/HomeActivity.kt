package com.example.robert.examen3parcial

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.dialogabout.*
import kotlinx.android.synthetic.main.dialogmetodos.*
import org.json.JSONException
import org.json.JSONObject

class HomeActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    //QR Code Scanner Object
    private var qrScanIntegrator: IntentIntegrator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        qrScanIntegrator = IntentIntegrator(this)
        qrScanIntegrator?.setOrientationLocked(false)

        mAuth = FirebaseAuth.getInstance()

        var welcome = tvWelcome.text.toString()
        tvWelcome.setText(welcome +" "+ intent.extras.getString("emailUser"))

        btnLeerplaca.setOnClickListener {
            scanQR()
        }

        btnMetodos.setOnClickListener {
            dialogMetodos()
        }

        btngoToVehiculos.setOnClickListener {
            val intent = Intent(this,VehiculosActivity::class.java)
            this.startActivity(intent)
        }
    }

    fun dialogAbout(){
        val myDialogView = layoutInflater.inflate(R.layout.dialogabout,null)
        val mBuilder = AlertDialog.Builder(this).setView(myDialogView)
        val mAlertDialog = mBuilder.show()

        mAlertDialog.btnCerrar.setOnClickListener {
            mAlertDialog.dismiss()
        }

        mAlertDialog.btnWeb.setOnClickListener {
            val uris = Uri.parse("https://github.com/robertojf2012?tab=repositories")
            val intents = Intent(Intent.ACTION_VIEW, uris)
            startActivity(intents)
        }
    }

    fun dialogMetodos(){
        val myDialogView = layoutInflater.inflate(R.layout.dialogmetodos,null)
        val mBuilder = AlertDialog.Builder(this).setView(myDialogView)
        val mAlertDialog = mBuilder.show()

        var metodoPago = ""
        var pagar = ""

        mAlertDialog.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if(R.id.radioEfectivo == checkedId){
                metodoPago = "Has seleccionado Efectivo"
                pagar = "Efectivo"
            }
            if(R.id.radioTarjeta == checkedId){
                metodoPago = "Has seleccionado Tarjeta"
                pagar = "Tarjeta"
            }
            if(R.id.radioTransferencia == checkedId){
                metodoPago = "Has seleccionado Transferencia"
                pagar = "Transferencia"
            }
            Toast.makeText(this,metodoPago,Toast.LENGTH_SHORT).show()
        }

        mAlertDialog.btnPagar.setOnClickListener {
            Toast.makeText(this,"Has pagado con "+pagar,Toast.LENGTH_SHORT).show()
        }
    }

    fun logout(){
        mAuth!!.signOut()
        val intent = Intent(this,MainActivity::class.java)
        this.startActivity(intent)
    }

    //METHODS FOR QR SCANNER
    private fun scanQR() {
        qrScanIntegrator?.initiateScan()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result != null) {

            // If QRCode has no data.
            if (result.contents == null) {
                Toast.makeText(this,"Result Not Found", Toast.LENGTH_LONG).show()
            }else {
                try {

                    // Converting the data to json format
                    val obj = JSONObject(result.contents)
                    // Show values in UI.
                    tvMarca.setText(obj.getString("Marca"))
                    tvModelo.setText(obj.getString("Modelo"))
                    tvAno.setText(obj.getString("Ano"))
                    tvDeuda.setText(obj.getString("Deuda"))

                }catch (e: JSONException) {
                    e.printStackTrace()
                    // Data not in the expected format. So, whole object as toast message.
                    Toast.makeText(this, result.contents, Toast.LENGTH_LONG).show()
                }
            }
        }else {
            Toast.makeText(this,"Null!", Toast.LENGTH_LONG).show()
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    //FOR THE MENU
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.btnAbout){
            dialogAbout()
        }
        if(item.itemId == R.id.btnLogout){
            logout()
        }
        return true
    }
}
