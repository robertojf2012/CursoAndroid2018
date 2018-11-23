package com.example.robert.examen3parcial

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mAuth = FirebaseAuth.getInstance()

        var welcome = tvWelcome.text.toString()
        tvWelcome.setText(welcome +" "+ intent.extras.getString("emailUser"))
    }

    fun logout(){
        mAuth!!.signOut()
        val intent = Intent(this,MainActivity::class.java)
        this.startActivity(intent)
    }

    //FOR THE MENU
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.btnAbout){
            Toast.makeText(this,"ABOUT",Toast.LENGTH_SHORT).show()
        }
        if(item.itemId == R.id.btnLogout){
            logout()
        }
        return true
    }
}
