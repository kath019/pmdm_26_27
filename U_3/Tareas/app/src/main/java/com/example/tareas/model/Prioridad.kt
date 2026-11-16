package com.example.tareas.model

import androidx.compose.ui.graphics.Color

enum class Prioridad(val label: String, val color: Color) {

    ALTA("Alta", Color(0xFFE53935)),

    MEDIA("Media", Color(0xFFFB8C00)),

    BAJA("Baja", Color(0xFF43A047))

}