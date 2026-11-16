package com.example.tareas.model

data class Tarea(
    val id: Int,
    val titulo: String,
    val descripcion: String = "",
    val completada: Boolean = false,
    val prioridad: Prioridad = Prioridad.MEDIA,
    val categoria: Categoria = Categoria.PERSONAL,
    val fechaLimite: String? = null, //"YYYY-MM-DD" o null
    val fechaCreacion: String //"YYYY-MM-DD"
)