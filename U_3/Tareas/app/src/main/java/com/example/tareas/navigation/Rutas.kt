package com.example.tareas.navigation

object Rutas{
    const val LISTA = "lista"
    const val DETALLE = "detalle/{id}"
    const val FORMULARIO = "formulario"

    fun detalle(id: Int): String{
        return "detalle/$id"
    }
}