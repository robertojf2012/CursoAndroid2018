package com.example.robert.favoritoapp

data class FavoritoResult(val favoritos:List<Item>) // val favoritos es la clave que tiene el arreglo de JSON

//Es el modelo que va a responder el JSON
data class Item(
        val id: String?,
        val title: String?,
        val description: String?,
        val url: String?
)