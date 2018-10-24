package com.example.robert.plazasapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_plaza.view.*

class PlazaDetail : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var tvNombre: TextView
    private lateinit var tvDescripcion: TextView
    private lateinit var tvTelefono: TextView
    private lateinit var ivImagen: ImageView

    override fun onMapReady(map: GoogleMap) {

        val marker = LatLng(intent.extras.getString("latitud").toDouble(), intent.extras.getString("longitud").toDouble())
        map.addMarker(MarkerOptions().position(marker).title("Marker in Sydney"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(marker,14f))

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plaza_detail)
        //Show the map...
        val mapFragment = supportFragmentManager.findFragmentById(R.id.googleMap) as SupportMapFragment
        mapFragment.getMapAsync(this)

        tvNombre = findViewById(R.id.tvNombre)
        tvDescripcion = findViewById(R.id.tvDescripcion)
        tvTelefono = findViewById(R.id.tvTelefono)
        ivImagen = findViewById(R.id.ivImagen)

        tvNombre.text = intent.extras.getString("nombre")
        tvDescripcion.text = intent.extras.getString("descripcion")
        tvTelefono.text = intent.extras.getString("telefono")

        Picasso.get().load(intent.extras.getString("imagen")).into(ivImagen)


    }
}
