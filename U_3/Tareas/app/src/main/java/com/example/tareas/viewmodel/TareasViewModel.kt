package com.example.tareas.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tareas.model.Tarea
import com.example.tareas.model.Prioridad
import com.example.tareas.model.Categoria
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TareasViewModel : ViewModel() {

    // Estado inicial sin cambios
    private val _uiState = MutableStateFlow(
        TareasUiState(
            tareas = listOf(
                Tarea(
                    id = 1,
                    titulo="Dormir 8h",
                    descripcion = "Recomendación médica",
                    prioridad = Prioridad.MEDIA,
                    categoria = Categoria.SALUD,
                    fechaCreacion = "2026-11-15"
                ),

                Tarea(
                    id=2,
                    titulo = "Deberes Acceso a Datos",
                    descripcion = "Completar ejercicios",
                    prioridad = Prioridad.ALTA,
                    categoria = Categoria.ESTUDIOS,
                    fechaLimite = "2026-11-17",
                    fechaCreacion = "2026-11-15"
                ),

                Tarea(
                    id=3,
                    titulo = "Ordenar habitación",
                    prioridad = Prioridad.BAJA,
                    categoria = Categoria.HOGAR,
                    fechaCreacion = "2026-11-14"
                ),

                Tarea(
                    id = 4,
                    titulo = "Comprar comida",
                    descripcion = "Comprar verduras, fruta y huevos",
                    prioridad = Prioridad.MEDIA,
                    categoria = Categoria.PERSONAL,
                    fechaLimite = "2026-11-18",
                    fechaCreacion = "2026-11-13"
                ),

                Tarea(
                    id = 5,
                    titulo = "Hacer ejercicio",
                    completada = true,
                    prioridad = Prioridad.MEDIA,
                    categoria = Categoria.SALUD,
                    fechaCreacion = "2026-11-10"
                ),

                Tarea(
                    id = 6,
                    titulo = "Preparar presentación",
                    descripcion = "Revisar las diapositivas",
                    prioridad = Prioridad.ALTA,
                    categoria = Categoria.TRABAJO,
                    completada = true,
                    fechaLimite = "2026-11-14",
                    fechaCreacion = "2026-11-08"
                ),

                Tarea(
                    id = 7,
                    titulo = "Leer un libro",
                    prioridad = Prioridad.BAJA,
                    categoria = Categoria.PERSONAL,
                    fechaLimite = "2026-11-25",
                    fechaCreacion = "2026-11-15"
                ),

                Tarea(
                    id = 8,
                    titulo = "Estudiar inglés",
                    descripcion = "Practicar vocabulario",
                    prioridad = Prioridad.MEDIA,
                    categoria = Categoria.ESTUDIOS,
                    fechaCreacion = "2026-11-16"
                )

            )
        ))

    val uiState: StateFlow<TareasUiState> = _uiState.asStateFlow()

    // Lista de tareas que cumplen los filtros seleccionados
    val tareasFiltradas: StateFlow<List<Tarea>> = _uiState.map {
        estado -> estado.tareas.filter {
            tarea ->

                val coincideEstado = when (estado.filtroEstado) {
                    FiltroEstado.TODAS -> true
                    FiltroEstado.PENDIENTES -> !tarea.completada
                    FiltroEstado.COMPLETADAS -> tarea.completada
                }

                val coincideCategoria =
                    estado.filtroCategoria == null || tarea.categoria == estado.filtroCategoria
                coincideEstado && coincideCategoria
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    // Agregar tarea
    fun agregarTarea(tarea: Tarea) {
        _uiState.update { estado ->
            estado.copy(tareas = estado.tareas + tarea)
        }
    }

    // Actualizar
    fun actualizarTarea(tarea: Tarea) {
        _uiState.update { estado ->
            estado.copy(
                tareas = estado.tareas.map {
                    if (it.id == tarea.id) tarea else it
                }
            )
        }
    }

    // Eliminar
    fun eliminarTarea(id: Int) {
        _uiState.update { estado ->
            estado.copy(
                tareas = estado.tareas.filter { it.id != id }
            )
        }
    }

    // Marcar una como completada o pendiente
    fun toggleCompletada(id: Int) {
        _uiState.update { estado ->
            estado.copy(
                tareas = estado.tareas.map {
                    if (it.id == id) {
                        it.copy(completada = !it.completada)
                    } else {
                        it
                    }
                }
            )
        }
    }

    // Cambiar el filtro de estado
    fun setFiltroEstado(filtro: FiltroEstado) {
        _uiState.update {
            it.copy(filtroEstado = filtro)
        }
    }

    // Cambiar el filtro de categoría
    fun setFiltroCategoria(categoria: Categoria?) {
        _uiState.update {
            it.copy(filtroCategoria = categoria)
        }
    }

    // Cambiar el modo claro u oscuro
    fun toggleModoOscuro() {
        _uiState.update {
            it.copy(modoOscuro = !it.modoOscuro)
        }
    }

    // Buscar una tarea por su identificador
    fun obtenerTarea(id: Int): Tarea? {
        return uiState.value.tareas.find { it.id == id }
    }
}
