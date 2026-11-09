package com.example.multipantalla_con_navegacion.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.multipantalla_con_navegacion.listado.PantallaListado
import com.example.multipantalla_con_navegacion.model.recetasIniciales
import com.example.multipantalla_con_navegacion.ui.detalle.PantallaDetalle
import com.example.multipantalla_con_navegacion.ui.formulario.PantallaFormulario

object Rutas {
    const val LISTADO = "listado"
    const val DETALLE = "detalle/{recetaId}"
    const val CREAR = "crear"
    const val EDITAR = "editar/{recetaId}"

    fun detalle(id: Int) = "detalle/$id"

    fun editar(id: Int) = "editar/$id"
}

@Composable
fun AppNavigation() {

    //para navegar entre pantallas
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Rutas.LISTADO) {
        composable(Rutas.LISTADO) {
            PantallaListado(navController)
        }

        composable(route = Rutas.DETALLE,
            arguments = listOf(
            navArgument("recetaId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("recetaId")
            val receta = recetasIniciales.find { it.id== id }

            if (receta != null) {
                PantallaDetalle(
                    receta= receta,
                    navController= navController
                )
            }
        }

        composable(Rutas.CREAR) {
            PantallaFormulario(navController)
        }

        composable(route = Rutas.EDITAR,
            arguments = listOf(navArgument("recetaId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("recetaId")
            val receta = recetasIniciales.find{it.id == id}
            if(receta != null){
                PantallaFormulario(navController = navController, receta=receta)
            }
            Text("Editar receta: $id")
        }
    }
}