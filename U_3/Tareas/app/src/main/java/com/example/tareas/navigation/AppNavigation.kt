package com.example.tareas.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tareas.model.Tarea
import com.example.tareas.ui.detalle.PantallaDetalle
import com.example.tareas.ui.formulario.PantallaFormulario
import com.example.tareas.ui.lista.PantallaLista
import com.example.tareas.viewmodel.TareasViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val tareasViewModel: TareasViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Rutas.LISTA
    ) {
        //pantalla de la Lista
        composable(Rutas.LISTA) {
            PantallaLista(
                viewModel = tareasViewModel,
                onNuevaTarea = {
                    navController.navigate(Rutas.FORMULARIO)
                },
                onTareaClick = { id ->
                    navController.navigate(Rutas.detalle(id))
                }
            )
        }
        //pantalla de detalle
        composable(
            route = Rutas.DETALLE,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            entrada ->
            val id = entrada.arguments?.getInt("id") ?: -1
            val tarea = tareasViewModel.obtenerTarea(id)
            if (tarea != null) {
                PantallaDetalle(
                    tarea = tarea,
                    onVolver = {navController.popBackStack()},
                    onEditar = {navController.navigate(Rutas.editar(tarea.id))}
                )
            }
        }
        //pantalla del formulario
        composable(Rutas.FORMULARIO) {

            PantallaFormulario(
                onGuardar = { titulo, descripcion, prioridad, categoria, fechaLimite ->

                    val nuevaTarea = Tarea(
                        id = System.currentTimeMillis().toInt(),
                        titulo = titulo,
                        descripcion = descripcion,
                        prioridad = prioridad,
                        categoria = categoria,
                        fechaLimite = fechaLimite,
                        fechaCreacion = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                    )

                    tareasViewModel.agregarTarea(nuevaTarea)

                    navController.popBackStack()
                }
            )
        }
        // Pantalla para editar una tarea
        composable(
            route = Rutas.EDITAR,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { entrada ->
            val id = entrada.arguments?.getInt("id") ?: -1
            val tarea = tareasViewModel.obtenerTarea(id)

            if (tarea != null) {
                PantallaFormulario(
                    tarea = tarea,
                    onGuardar = { titulo, descripcion, prioridad, categoria, fechaLimite ->

                        val tareaActualizada = tarea.copy(
                            titulo = titulo,
                            descripcion = descripcion,
                            prioridad = prioridad,
                            categoria = categoria,
                            fechaLimite = fechaLimite
                        )
                        tareasViewModel.actualizarTarea(tareaActualizada)
                        navController.popBackStack()
                    }
                )
            }
        }


    }
}