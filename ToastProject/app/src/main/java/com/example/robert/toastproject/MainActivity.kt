package com.example.robert.toastproject

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.graphics.Color.parseColor



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnOpenToast = findViewById<Button>(R.id.btn_open_toast)
        var fondo = findViewById<ConstraintLayout>(R.id.fondo)

        btnOpenToast.setOnClickListener(View.OnClickListener {

            Toast.makeText(this,"Mensaje toast!!",Toast.LENGTH_SHORT).show()

            fondo.setBackgroundColor(Color.GREEN)

            Log.d("LOG","MENSAJEE")

        })

    }
}
