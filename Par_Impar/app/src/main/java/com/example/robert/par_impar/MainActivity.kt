package com.example.robert.par_impar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var etNumber = findViewById<EditText>(R.id.etNumber)
        var btnValidar = findViewById<Button>(R.id.btnValidar)

        btnValidar.setOnClickListener {
            var number =  etNumber.text.toString().toInt()

            if(number%2==0){
                Toast.makeText(this,"Numero PAR",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Numero IMPAR",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
