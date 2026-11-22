package com.example.tareas.navigation

object Rutas{
    const val LISTA = "lista"
    const val DETALLE = "detalle/{id}"
    const val FORMULARIO = "formulario"

    const val EDITAR = "editar/{id}"

    fun detalle(id: Int): String{
        return "detalle/$id"
    }

    fun editar(id: Int): String {
        return "editar/$id"
    }
}