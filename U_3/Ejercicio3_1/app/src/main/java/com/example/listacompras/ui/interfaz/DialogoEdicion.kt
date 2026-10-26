package com.example.listacompras.ui.interfaz

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DialogoEdicion(
    nombreActual: String,
    onGuardar: (String) -> Unit,
    onCancelar: () -> Unit
) {
    var nombreEditado by remember(nombreActual) {
        mutableStateOf(nombreActual)
    }

    AlertDialog(
        onDismissRequest = onCancelar,
        title = {
            Text("Editar producto")
        },
        text = {
            Column {
                OutlinedTextField(
                    value = nombreEditado,
                    onValueChange = { nombreEditado = it },
                    label = { Text("Nombre del producto")
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val nombre = nombreEditado.trim()

                    if (nombre.isNotEmpty()) {
                        onGuardar(nombre)
                    }
                }
            ) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onCancelar
            ) {
                Text("Cancelar")
            }
        }
    )
}
