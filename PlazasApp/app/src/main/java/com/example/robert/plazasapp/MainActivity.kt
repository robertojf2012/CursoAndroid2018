package com.example.robert.plazasapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvPlazas.layoutManager = LinearLayoutManager(this)

        val url = "https://plazasapp.herokuapp.com/api/plazasMobile"

        if(isNetworkConnected()){
            //para usar doAsync necesitamos libreria anko
            doAsync {
                val result = Request(url).run()
                uiThread {
                    //longToast("com.example.robert.favoritoapp.Request performed")
                    lvPlazas.adapter = PlazaAdapter(result,this@MainActivity)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuNuevaPlaza){
            val intent = Intent(this,NuevaPlaza::class.java)
            this.startActivity(intent)
        }
        return true
    }

    override fun onResume(){
        super.onResume()

        val url = "https://plazasapp.herokuapp.com/api/plazasMobile"

        if(isNetworkConnected()){
            //para usar doAsync necesitamos libreria anko
            doAsync {
                val result = Request(url).run()
                uiThread {
                    //longToast("com.example.robert.favoritoapp.Request performed")
                    lvPlazas.adapter = PlazaAdapter(result,this@MainActivity)
                }
            }
        }else{
            Toast.makeText(this,"No hay conexion a internet", Toast.LENGTH_SHORT).show()
        }
    }
}
