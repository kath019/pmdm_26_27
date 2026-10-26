package com.example.listacompras.ui.interfaz

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.listacompras.model.Producto

@Composable
fun ProductoItem(
    producto: Producto,
    onCambiarCompra: (Producto) -> Unit,
    onEliminar: (Producto) -> Unit,
    onEditar: (Producto) -> Unit) {
    Row( modifier = Modifier .fillMaxWidth() .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically ) {
        Checkbox( checked = producto.comprado,
            onCheckedChange = { onCambiarCompra(producto) } )
        //que se vea la casilla tachada
        Text(
            text = producto.nombre,
            textDecoration = if (producto.comprado) {
                //línea que atraviesa el texto
                TextDecoration.LineThrough
            } else {
                //texto normal
                TextDecoration.None
            },
            //transparencia del elemento
            modifier = Modifier.weight(1f).alpha(
                if (producto.comprado) 0.5f else 1f
                //combinedClickable permite definir los siguientes dos comportamientos
            ).combinedClickable(onClick = {}, onLongClick = { onEditar(producto)})
        )

        //botón de eliminar cada producto
        IconButton(onClick = {onEliminar(producto)}) {
            Text("X")
        }
    }
}