package com.example.tareas.viewmodel

import com.example.tareas.model.Tarea
import com.example.tareas.model.Categoria

data class TareasUiState(
    val tareas: List<Tarea> = emptyList(),
    val filtroEstado: FiltroEstado = FiltroEstado.TODAS,
    val filtroCategoria: Categoria? = null,
    val modoOscuro: Boolean = false
)

enum class FiltroEstado {
    TODAS,
    PENDIENTES,
    COMPLETADAS
}