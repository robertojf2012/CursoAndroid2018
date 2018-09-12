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

        btnValidar.setBackgroundResource(R.drawable.smile)

        btnValidar.setOnClickListener {

            var number =  etNumber.text.toString()

            if(number.isEmpty()){
                Toast.makeText(this,"Escribe un numero",Toast.LENGTH_SHORT).show()
            }else{
                if(number.toInt() == 0){
                    Toast.makeText(this,"Ingresa numero diferente de cero",Toast.LENGTH_SHORT).show()
                }else{
                    if(number.toInt()%2==0){
                        Toast.makeText(this,"Numero PAR",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this,"Numero IMPAR",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
