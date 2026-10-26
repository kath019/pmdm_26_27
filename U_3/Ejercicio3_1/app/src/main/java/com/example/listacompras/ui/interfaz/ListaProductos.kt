package com.example.listacompras.ui.interfaz

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.listacompras.model.Producto

@Composable
fun ListaProductos(
    productos: List<Producto>,
    onCambiarCompra: (Producto) -> Unit,
    onEliminar: (Producto) -> Unit,
    onEditar: (Producto) -> Unit,
    modifier: Modifier = Modifier
) {
    //se crea la lista desplazable
    LazyColumn(
        modifier = modifier
    ) {//se recorren los productos
        items(
            //ordenado según si está comprado
            items = productos.sortedBy { producto -> producto.comprado},
            key = { producto -> producto.id }
        ) { producto -> ProductoItem(producto = producto, onCambiarCompra = onCambiarCompra, onEliminar = onEliminar, onEditar = onEditar)
        }
    }
}
