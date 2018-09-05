package com.example.robert.proyectokotlin

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

data class Cursos(val nombre: String, val url: String)

class AprendiendoKotlin : Activity() {

    val react = Cursos("React","react")
    val kot = Cursos("Kotlin","kotlin")
    var cursoActual = react.copy()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aprendiendo_kotlin)

        //usamos val cuando estamos seguros que la variable no va a cambiar.. var es una variable que si puede cambiar

        val boton = findViewById<Button>(R.id.botonsito)

        boton.setOnClickListener(View.OnClickListener {
            switchCurso(cursoActual)
        })

       //txt.setText("Curso de ${react.nombre} en platsi.com/${react.url}")

    }

    fun switchCurso(curso:Cursos){
        val txt = findViewById<TextView>(R.id.mensaje)
        var c = curso.copy()

        when(curso.url){
            "react" -> cursoActual = kot.copy()
            "kotlin" -> cursoActual = react.copy()
            else -> print("Esto no va a pasar")
        }
        txt.setText("Curso de ${c.nombre} en platsi.com/${c.url}")
    }
}
