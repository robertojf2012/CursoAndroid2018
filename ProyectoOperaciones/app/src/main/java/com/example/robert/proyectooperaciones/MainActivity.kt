package com.example.robert.proyectooperaciones

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var number1 = findViewById<EditText>(R.id.txtNumber1)
        var number2 = findViewById<EditText>(R.id.txtNumber2)
        var result = findViewById<TextView>(R.id.lblResult)
        var buttonSumar = findViewById<Button>(R.id.btnSumar)
        var background = findViewById<ConstraintLayout>(R.id.colorLayout)

        var foto = findViewById<ImageView>(R.id.imgAndroid)

        buttonSumar.setOnClickListener {

            var number1Value = number1.text.toString()
            var number2Value = number2.text.toString()

            var resultado = number1Value.toInt() + number2Value.toInt()

            var promedio = (number1Value.toInt()+number2Value.toInt())/2

            if(promedio>=7){
                background.setBackgroundColor(Color.GREEN)
                foto.setImageResource(R.drawable.cat)
            }else{
                background.setBackgroundColor(Color.RED)
            }

            result.setText("El resultado es ${resultado}")

            val inputManager:InputMethodManager =getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.SHOW_FORCED)

        }


    }
}
