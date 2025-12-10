package com.example.miprimerapp

import com.google.firebase.firestore.PropertyName

data class Producto(
    val codigo_barra: String = "",
    val nombre: String = "",
    val precio: Int = 0,
    @get:PropertyName("categoria")
    val categoria: String = "",
    val url: String = ""
)
