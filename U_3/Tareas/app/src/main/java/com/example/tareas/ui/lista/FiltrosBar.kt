package com.example.tareas.ui.lista

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tareas.model.Categoria
import com.example.tareas.viewmodel.FiltroEstado

@Composable
fun FiltrosBar(
    filtroEstado: FiltroEstado,
    filtroCategoria: Categoria?,
    onEstadoChange: (FiltroEstado) -> Unit,
    onCategoriaChange: (Categoria?) -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(text = "Estado")

        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            FilterChip(
                selected = filtroEstado == FiltroEstado.TODAS,
                onClick = {
                    onEstadoChange(FiltroEstado.TODAS)
                },
                label = { Text("Todas") }
            )

            FilterChip(
                selected = filtroEstado == FiltroEstado.PENDIENTES,
                onClick = {
                    onEstadoChange(FiltroEstado.PENDIENTES)
                },
                label = { Text("Pendientes") }
            )

            FilterChip(
                selected = filtroEstado == FiltroEstado.COMPLETADAS,
                onClick = {
                    onEstadoChange(FiltroEstado.COMPLETADAS)
                },
                label = { Text("Completadas") }
            )
        }

        Text(text = "Categoría")

        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            FilterChip(
                selected = filtroCategoria == null,
                onClick = {
                    onCategoriaChange(null)
                },
                label = { Text("Todas") }
            )

            Categoria.values().forEach { categoria ->

                FilterChip(
                    selected = filtroCategoria == categoria,
                    onClick = {
                        onCategoriaChange(categoria)
                    },
                    label = {
                        Text("${categoria.emoji} ${categoria.label}")
                    }
                )
            }
        }
    }
}