package com.example.listacompras.ui.interfaz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun CampoNuevoProducto(
    texto: String,
    onTextoChange: (String) -> Unit,
    onAgregar: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        OutlinedTextField(
            value = texto,
            onValueChange = onTextoChange,
            label = {
                Text("Nuevo producto")
            },
            modifier = Modifier.weight(1f),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onAgregar()
                }
            )
        )

        IconButton(
            onClick = onAgregar
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Añadir producto"
            )
        }
    }

}