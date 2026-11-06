package com.example.multipantalla_con_navegacion.ui.detalle

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.multipantalla_con_navegacion.model.Receta
import com.example.multipantalla_con_navegacion.navigation.Rutas


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalle(
    receta: Receta,
    navController: NavController
) {

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text(receta.nombre)
                },
                navigationIcon = {
                    TextButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Text("Volver")
                    }
                },
                actions = {
                    TextButton(
                        onClick = {
                            navController.navigate(
                                Rutas.editar(receta.id)
                            )
                        }
                    ) {
                        Text("Editar")
                    }
                }
            )
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {

            Text(
                text = receta.imagenEmoji,
                style = MaterialTheme.typography.displayLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = receta.nombre,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Categoría: ${receta.categoria}")

            Text("Tiempo: ${receta.tiempoMinutos} minutos")

            Text("Dificultad: ${receta.dificultad}")

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Ingredientes",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            receta.ingredientes.forEach { ingrediente ->

                Text("• $ingrediente")

            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Preparación",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            receta.pasos.forEachIndexed { index, paso ->

                Text("${index + 1}. $paso")

                Spacer(modifier = Modifier.height(8.dp))

            }
        }
    }
}