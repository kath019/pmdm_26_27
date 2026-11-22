package com.example.tareas.ui.lista

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tareas.viewmodel.TareasViewModel

@Composable
fun PantallaLista(
    viewModel: TareasViewModel = viewModel(),
    onNuevaTarea: () -> Unit = {},
    onTareaClick: (Int) -> Unit = {}
) {
    val estado by viewModel.uiState.collectAsState()
    val tareas by viewModel.tareasFiltradas.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Mis tareas",
            style = MaterialTheme.typography.headlineMedium
        )

        Button(
            onClick = { viewModel.toggleModoOscuro() }
        ) {
            Text(
                text = if (estado.modoOscuro) {
                    "☀️ Modo claro"
                } else {
                    "🌙 Modo oscuro"
                }
            )
        }

        Button(
            onClick = {onNuevaTarea()
        },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "+ Nueva tarea")
        }
        FiltrosBar(
            filtroEstado = estado.filtroEstado,
            filtroCategoria = estado.filtroCategoria,
            onEstadoChange = {
                viewModel.setFiltroEstado(it)
            },
            onCategoriaChange = {
                viewModel.setFiltroCategoria(it)
            }
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(tareas) { tarea ->

                TareaItem(
                    tarea = tarea,
                    onCompletada = {
                        viewModel.toggleCompletada(tarea.id)
                    },
                    onClick = {onTareaClick(tarea.id)}
                )
            }
        }
    }
}