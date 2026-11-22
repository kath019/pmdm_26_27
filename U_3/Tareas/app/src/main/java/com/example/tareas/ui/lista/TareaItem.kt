package com.example.tareas.ui.lista

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
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
fun TareaItem(
    tarea: Tarea,
    onCompletada: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            Checkbox(
                checked = tarea.completada,
                onCheckedChange = {
                    onCompletada()
                }
            )

            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    text = tarea.titulo,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${tarea.categoria.emoji} ${tarea.categoria.label}"
                )
                Text(
                    text = "Prioridad: ${tarea.prioridad.label}"
                )
                Text(
                    text = if (tarea.completada) {
                        "Completada"
                    } else {
                        "Pendiente"
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TareaItemPreview() {

    TareasTheme(darkTheme = false) {

        TareaItem(
            tarea = Tarea(
                id = 1,
                titulo = "Estudiar Kotlin",
                descripcion = "Repasar Jetpack Compose",
                prioridad = Prioridad.ALTA,
                categoria = Categoria.ESTUDIOS,
                fechaCreacion = "22/11/2026"
            ),
            onCompletada = {},
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TareaItemPreviewOscuro() {

    TareasTheme(darkTheme = true) {

        TareaItem(
            tarea = Tarea(
                id = 2,
                titulo = "Hacer proyecto DAM",
                descripcion = "Terminar la aplicación de tareas",
                prioridad = Prioridad.MEDIA,
                categoria = Categoria.ESTUDIOS,
                fechaCreacion = "22/11/2026"
            ),
            onCompletada = {},
            onClick = {}
        )
    }
}