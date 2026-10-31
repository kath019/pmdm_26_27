package com.example.multipantalla_con_navegacion.model

data class Receta(
    val id: Int,
    val nombre: String,
    val categoria: String,          // "Entrante", "Principal", "Postre", "Bebida"
    val tiempoMinutos: Int,
    val dificultad: String,         // "Fácil", "Media", "Difícil"
    val ingredientes: List<String>,
    val pasos: List<String>,
    val imagenEmoji: String = "🍽️"  // Emoji como placeholder de imagen
)