package com.example.tareas.ui.formulario

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tareas.model.Categoria
import com.example.tareas.model.Prioridad
import com.example.tareas.model.Tarea
import com.example.tareas.ui.theme.TareasTheme

@Composable
fun PantallaFormulario(
    onGuardar: (String, String, Prioridad, Categoria, String?) -> Unit,
    tarea: Tarea? = null) {

    var titulo by remember(tarea) { mutableStateOf(tarea?.titulo ?:"") }
    var descripcion by remember(tarea) { mutableStateOf(tarea?.descripcion ?:"") }

    var mostrarError by remember { mutableStateOf(false) }

    var prioridad by remember(tarea) { mutableStateOf(tarea?.prioridad ?:Prioridad.MEDIA) }
    var categoria by remember(tarea) { mutableStateOf(tarea?.categoria ?:Categoria.PERSONAL) }

    var fechaLimite by remember(tarea) { mutableStateOf(tarea?.fechaLimite ?: "") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = if(tarea== null) {
                "Nueva tarea"
            } else{
                "Editar tarea"
            })

        OutlinedTextField(
            value = titulo,
            onValueChange = {
                titulo = it
                mostrarError = false
            },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth(),
            isError = mostrarError,
            supportingText = {
                if (mostrarError) {
                    Text("El título es obligatorio")
                }
            }
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = fechaLimite,
            onValueChange = { fechaLimite = it },
            label = { Text("Fecha límite (DD/MM/AAAA)") },
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = "Prioridad: ${prioridad.label}")

        Button(
            onClick = { prioridad = Prioridad.ALTA }
        ) {
            Text("Alta")
        }

        Button(
            onClick = { prioridad = Prioridad.MEDIA }
        ) {
            Text("Media")
        }

        Button(
            onClick = { prioridad = Prioridad.BAJA }
        ) {
            Text("Baja")
        }

        Text(text = "Categoría: ${categoria.label}")

        Categoria.values().forEach { opcion ->

            Button(
                onClick = {categoria = opcion}
            ) {
                Text("${opcion.emoji} ${opcion.label}")
            }
        }

        Button(
            onClick = {
                if (titulo.isBlank()) {
                    mostrarError = true
                } else {
                    onGuardar(titulo, descripcion, prioridad, categoria, fechaLimite.ifBlank { null })
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (tarea == null) {
                    ("Crear tarea")
                } else {
                    "Guardar cambios"
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaFormularioPreview() {

    TareasTheme(darkTheme = false) {

        PantallaFormulario(
            onGuardar = { _, _, _, _, _ -> }
        )
    }
}