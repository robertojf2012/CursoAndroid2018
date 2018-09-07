package com.example.robert.piedrapapeltijera

import android.content.Context
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.goodcodeforfun.seismik.ShakeDetector
import java.util.*

class MainActivity : AppCompatActivity(), ShakeDetector.Listener {

    private lateinit var shakeDetector: ShakeDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shakeDetector = ShakeDetector(this)

        var btn_ppt = findViewById<Button>(R.id.btn_ppt)

        btn_ppt.setOnClickListener {
            onshake()
        }
    }

    fun onshake(){
        var iv_fotos = findViewById<ImageView>(R.id.iv_fotos)
        var number = (0..2).random()
        //Toast.makeText(this,"Numero: ${number}",Toast.LENGTH_SHORT).show()

        when(number){
            0-> iv_fotos.setImageResource(R.drawable.rock)
            1-> iv_fotos.setImageResource(R.drawable.paper)
            2-> iv_fotos.setImageResource(R.drawable.scissors)
        }
    }

    override fun hearShake() {
        onshake()
    }
    override fun onStart() {
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        shakeDetector.start(sensorManager)
        super.onStart()
    }

    override fun onStop() {
        shakeDetector.stop()
        super.onStop()
    }

    fun ClosedRange<Int>.random() = Random().nextInt((endInclusive + 1) - start) +  start
}
