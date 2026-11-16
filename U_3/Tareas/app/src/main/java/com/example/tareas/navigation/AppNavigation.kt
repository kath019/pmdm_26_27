package com.example.tareas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Rutas.LISTA
    ) {
        composable(Rutas.LISTA) {
            // Aquí irá la pantalla de tareas
        }
        composable(
            route = Rutas.DETALLE,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            // Aquí irá la pantalla de detalles
        }
        composable(Rutas.FORMULARIO) {
            // Aquí irá el formulario
        }
    }
}