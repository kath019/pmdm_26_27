package com.example.listacompras.ui.interfaz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.listacompras.model.Producto

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun PantallaListaCompras() {

    var productos by remember {
        mutableStateOf(listOf<Producto>())
    }

    var textoNuevo by remember {
        mutableStateOf("")
    }

    var siguienteId by remember {
        mutableIntStateOf(1)
    }

    var productoEnEdicion by remember {
        mutableStateOf<Producto?>(null)
    }

    var mostrarConfirmacionBorrado by remember {
        mutableStateOf(false)
    }

    fun agregarProducto(){
        //limpiar los espacios
        val nombre = textoNuevo.trim()

        if(nombre.isNotEmpty()) {
            val nuevoProducto = Producto(
                id = siguienteId,
                nombre=nombre,
                comprado= false
            )

            productos = productos + nuevoProducto
            siguienteId++
            textoNuevo = ""
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Lista de compras")
                }
            )
        }
    )

    { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            CampoNuevoProducto(texto = textoNuevo, onTextoChange = { textoNuevo = it },
                onAgregar = { agregarProducto() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            //comprueba si hay productos comprados -> aparece botón
            if (productos.any { it.comprado }) {
                androidx.compose.material3.Button(
                    onClick = { mostrarConfirmacionBorrado = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Eliminar productos comprados")
                }
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
            }

            if(productos.isEmpty()) {
                Text(
                    text = "No hay productos. ¡Añade algo!",
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                ListaProductos(
                    productos = productos,
                    onCambiarCompra = {productoSeleccionado ->

                        //recorrer los producto y crear una nueva lista a partir de ellos
                        productos = productos.map { producto ->

                            //identifica el producto seleccionado
                            if (producto.id == productoSeleccionado.id) {

                                //cambiar su estado
                                producto.copy(comprado = !producto.comprado)
                            } else {
                                producto
                            }
                        }
                     },

                    onEliminar = { productoSeleccionado ->
                        productos= productos.filter { producto ->
                            producto.id != productoSeleccionado.id
                        }
                    },

                    onEditar = { productoSeleccionado ->
                        productoEnEdicion = productoSeleccionado
                    },
                    modifier = Modifier.fillMaxWidth())
            }

        }
    }

    //comprieba si hay alhún producto seleccionado para editar y muestra el diálogo
    if (productoEnEdicion != null) {

        DialogoEdicion(
            nombreActual = productoEnEdicion!!.nombre,
            onGuardar = { nuevoNombre ->
                productos = productos.map { producto ->

                    if (producto.id == productoEnEdicion!!.id) {
                        producto.copy(nombre = nuevoNombre)
                    } else {
                        producto
                    }
                }

                productoEnEdicion = null
            },

            onCancelar = {
                productoEnEdicion = null
            }
        )
    }

    //mostrar la confirmación de si se han borrado
    if (mostrarConfirmacionBorrado) {
        AlertDialog(onDismissRequest = { mostrarConfirmacionBorrado = false
            },

            title = { Text("Eliminar productos comprados")
            },

            text = { Text("¿Seguro que quieres eliminar todos los productos comprados?"
                )
            },

            confirmButton = {
                TextButton(
                    onClick = {
                        productos = productos.filter { producto ->
                            !producto.comprado
                        }

                        mostrarConfirmacionBorrado = false
                    }
                ) {
                    Text("Eliminar")
                }
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        mostrarConfirmacionBorrado = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}