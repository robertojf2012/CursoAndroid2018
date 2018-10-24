package com.example.robert.plazasapp

data class PlazaResult(val plazas:List<Item>) // val plazas es la clave que tiene el arreglo de JSON

//Modelo que va a responder el JSON
data class Item(
        val id: String?,
        val nombre: String?,
        val descripcion: String?,
        val imagen: String?,
        val telefono: String?,
        val latitud: String?,
        val longitud: String?
)