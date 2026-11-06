package com.example.multipantalla_con_navegacion.listado

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.multipantalla_con_navegacion.model.recetasIniciales
import com.example.multipantalla_con_navegacion.navigation.Rutas


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaListado(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Mis Recetas")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate(Rutas.CREAR)
                }
            ) {Text("+")
            }
        }
    ) { padding ->
        if (recetasIniciales.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Todavía no hay recetas")
            }
        } else {
            //para mostrar las recetas una debajo de otra
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                //bucle para recorrer recetas
                items(recetasIniciales) { receta ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {navController.navigate(Rutas.detalle(receta.id))
                        }
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = receta.imagenEmoji,
                                style = MaterialTheme.typography.headlineLarge
                            )
                            Text(
                                text = receta.nombre,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = receta.categoria
                            )
                            Text(
                                text = "${receta.tiempoMinutos} minutos - ${receta.dificultad}"
                            )
                        }
                    }
                }
            }
        }
    }
}