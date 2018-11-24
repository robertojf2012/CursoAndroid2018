package com.example.robert.examen3parcial

data class AutoResult(val cars:List<Item>) // val cars es la clave que tiene el arreglo de JSON

//Modelo que va a responder el JSON
data class Item(
    val Marca: String?,
    val Modelo: String?,
    val Ano: Int?,
    val imagen: String?
)