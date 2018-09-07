package com.example.robert.imcapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txt_peso = findViewById<EditText>(R.id.txt_peso)
        var txt_estatura = findViewById<EditText>(R.id.txt_estatura)
        var btn_calcular = findViewById<Button>(R.id.btn_calcular)
        var tv_result = findViewById<TextView>(R.id.tv_result)

        //on button click
        btn_calcular.setOnClickListener {

            var peso = txt_peso.text.toString()
            var estatura = txt_estatura.text.toString()

            if(peso.equals("") || estatura.equals("")){
                Toast.makeText(this,"Debe ingresar todos los valores",Toast.LENGTH_SHORT).show()
            }else{
                var pesoVal = peso.toDouble()
                var estaturaVal = estatura.toDouble()
                calcularMasaCorporal(pesoVal,estaturaVal) //calling the method
            }
        }

    }

    fun calcularMasaCorporal(pesoEnKg:Double , estaturaEnMetros:Double){

        var imc = (pesoEnKg)/(estaturaEnMetros*estaturaEnMetros)
        var estatusDelPaciente: String

        if(imc >= 19 && imc <= 24.9){
            estatusDelPaciente = "El paciente se encuentra en el peso adecuado"
        }else{
            estatusDelPaciente = "El paciente NO se encuentra en el peso adecuado"
        }

        tv_result.setText(estatusDelPaciente)

    }


}
