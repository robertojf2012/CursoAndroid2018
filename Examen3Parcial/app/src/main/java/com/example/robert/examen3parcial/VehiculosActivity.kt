package com.example.robert.examen3parcial

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_vehiculos.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class VehiculosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehiculos)

        lvCars.layoutManager = LinearLayoutManager(this)

        val url = "https://examen3api.herokuapp.com/api/cars"

        if(isNetworkConnected()){
            //para usar doAsync necesitamos libreria anko
            doAsync {
                val result = Request(url).run()
                uiThread {
                    lvCars.adapter = CarAdapter(result,this@VehiculosActivity)
                }
            }
        }else{
            Toast.makeText(this,"No hay conexion a internet", Toast.LENGTH_SHORT).show()
        }
    }

    //Function to check internet connectivity
    fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager //1
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
