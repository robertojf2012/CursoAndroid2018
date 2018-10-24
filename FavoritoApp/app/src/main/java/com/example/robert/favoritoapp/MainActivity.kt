package com.example.robert.favoritoapp

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private lateinit var listView:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        favoList.layoutManager = LinearLayoutManager(this)

       // listView = findViewById(R.id.favoList)

        val url = "https://favoritosapp.herokuapp.com/api/favoritosMobile"

        if(isNetworkConnected()){
            Toast.makeText(this,"CONECTADO A INTERNET",Toast.LENGTH_SHORT).show()

            //para usar doAsync necesitamos libreria anko

            doAsync {
                val result = Request(url).run()
                uiThread {
                    longToast("com.example.robert.favoritoapp.Request performed")
                    favoList.adapter = FavoritoAdapter(result,this@MainActivity)
                }
            }


        }else{
            Toast.makeText(this,"NO HAY CONEXION",Toast.LENGTH_SHORT).show()
        }


    }

    fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager //1
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
