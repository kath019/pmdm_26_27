package com.example.listacompras.model

data class Producto (
    val id: Int,
    val nombre: String,
    val comprado: Boolean = false
)