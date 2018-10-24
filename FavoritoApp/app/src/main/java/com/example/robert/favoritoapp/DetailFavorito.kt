package com.example.robert.favoritoapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DetailFavorito : AppCompatActivity(),OnMapReadyCallback {

    override fun onMapReady(googleMap: GoogleMap) {

        val sydney = LatLng(28.609408, -106.127955)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,14f))


    }

    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvUrl: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_favorito)

        tvTitle = findViewById(R.id.tvTitle)
        tvDescription = findViewById(R.id.tvDescription)
        tvUrl = findViewById(R.id.tvUrl)

        tvTitle.text = intent.extras.getString("title")
        tvDescription.text = intent.extras.getString("description")
        tvUrl.text = intent.extras.getString("url")

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }
}
