package com.example.tareas.ui.detalle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tareas.model.Categoria
import com.example.tareas.model.Prioridad
import com.example.tareas.model.Tarea
import com.example.tareas.ui.theme.TareasTheme

@Composable
fun PantallaDetalle(
    tarea: Tarea, onVolver: () -> Unit,
    onEditar: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = onVolver
        ) {
            Text(text = "← Volver")
        }
        Button (
            onClick = onEditar
        ) {
            Text(text = "Editar tarea")
        }
        Text(
            text = tarea.titulo,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(text = "Descripción: ${tarea.descripcion}")

        Text(text = "Categoría: ${tarea.categoria.emoji} ${tarea.categoria.label}")

        Text(text = "Prioridad: ${tarea.prioridad.label}")

        Text(
            text = if (tarea.completada) {
                "Estado: Completada"
            } else {
                "Estado: Pendiente"
            }
        )

        Text(text = "Fecha de creación: ${tarea.fechaCreacion}")

        Text(text = "Fecha límite: ${tarea.fechaLimite ?: "Sin fecha límite"}")
    }
}


@Preview(showBackground = true)
@Composable
fun PantallaDetallePreview() {

    TareasTheme(darkTheme = false) {

        PantallaDetalle(
            tarea = Tarea(
                id = 1,
                titulo = "Estudiar Kotlin",
                descripcion = "Repasar Jetpack Compose para el examen.",
                prioridad = Prioridad.ALTA,
                categoria = Categoria.ESTUDIOS,
                fechaCreacion = "22/11/2026",
                fechaLimite = "25/11/2026"
            ),
            onVolver = {},
            onEditar = {}
        )
    }
}