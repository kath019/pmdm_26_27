package com.example.multipantalla_con_navegacion.ui.formulario

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.multipantalla_con_navegacion.model.Receta
import com.example.multipantalla_con_navegacion.model.recetasIniciales

@OptIn(ExperimentalMaterial3Api ::class)
@Composable
//el formulario puede recibir una receta para editarla
fun PantallaFormulario(navController: NavController, receta: Receta? =null) {
    var nombre by remember(receta){
        mutableStateOf(receta?.nombre ?: "") }
    var tiempo by remember(receta) {
        mutableStateOf(receta?.tiempoMinutos?.toString() ?: "")}
    var categoria by remember(receta) {
        mutableStateOf(receta?.categoria ?: "Principal")}
    var dificultad by remember(receta) {
        mutableStateOf(receta?.dificultad ?: "Fácil") }

    var nuevoIngrediente by remember { mutableStateOf("")}
    val ingredientes = remember(receta) {
        mutableStateListOf<String>().apply {
            addAll(receta?.ingredientes ?: emptyList())
        }
    }
    var nuevoPaso by remember { mutableStateOf("") }
    val pasos = remember(receta) {
        mutableStateListOf<String>().apply {
            addAll(receta?.pasos ?: emptyList()) }}
    var mensajeError by remember {mutableStateOf("")}

    Scaffold(
        topBar = {
            TopAppBar(
                title ={Text(if (receta == null) "Nueva receta" else "Editar receta") },
                navigationIcon = { TextButton(onClick = {navController.popBackStack()}) {Text("X")}},
                actions = {TextButton(onClick = {
                    //comprobar que el tiempo sea un numero
                    val tiempoNumero=tiempo.toIntOrNull()
                    if(nombre.isBlank() || tiempoNumero ==null || tiempoNumero <= 0 ||ingredientes.isEmpty() || pasos.isEmpty()){
                        mensajeError = "Completa todos los campos, añade ingredientes y pasos"

                    } else {
                        if(receta == null){
                            //Se crea la nueva receta
                            val nuevaReceta = Receta(
                                id = (recetasIniciales.maxOfOrNull { it.id } ?: 0) +1,
                                nombre = nombre,
                                categoria = categoria,
                                tiempoMinutos=tiempoNumero,
                                dificultad= dificultad,
                                ingredientes = ingredientes.toList(),
                                pasos = pasos.toList()
                            )
                            recetasIniciales.add(nuevaReceta)

                        } else {
                            //editar una receta que ya existe
                            val indice = recetasIniciales.indexOfFirst { it.id == receta.id }

                            if (indice != -1) {
                                recetasIniciales[indice] = receta.copy(
                                    nombre = nombre,
                                    categoria = categoria,
                                    tiempoMinutos = tiempoNumero,
                                    dificultad = dificultad,
                                    ingredientes = ingredientes.toList(),
                                    pasos = pasos.toList()
                                )
                            }
                        }

                        //volver a la pagina anterior
                        navController.popBackStack()
                    }
                }) {Text("Guardar")}}
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).verticalScroll(rememberScrollState()).padding(16.dp)){
            Text("Nombre de la receta")
            OutlinedTextField(
                value=nombre,
                onValueChange =  {nombre = it},
                label = {Text("Nombre")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Categoría")
            Row{
                listOf("Entrante", "Principal", "Postre", "Bebida").forEach {
                    categoriaActual ->
                    FilterChip(
                        selected = categoria == categoriaActual,
                        onClick = { categoria = categoriaActual},
                        label ={ Text(categoriaActual)}
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Tiempo de preparación")
            OutlinedTextField(
                value = tiempo,
                onValueChange = {tiempo = it},
                label = {Text("Minutos")},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text("Dificultad")
            Row {
                listOf("Fácil", "Media", "Dificil").forEach {
                    dificultadActual -> FilterChip(
                        selected = dificultad ==dificultadActual,
                        onClick = {dificultad = dificultadActual},
                        label = {Text(dificultadActual)}
                    )
                }
            }

            if(mensajeError.isNotEmpty()) {
                Text(text = mensajeError, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text( text ="Ingredientes", style= MaterialTheme.typography.titleLarge)
            OutlinedTextField(
                value = nuevoIngrediente,
                onValueChange = {nuevoIngrediente = it} ,
                label = { Text("Nuevo Ingrediente")},
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = {
                //comprueba que el campo no este vacío
                if(nuevoIngrediente.isNotBlank()) {
                    ingredientes.add(nuevoIngrediente)
                    nuevoIngrediente = ""
                }
            }
            ) {
                Text("Añadir ingrediente")
            }

            ingredientes.forEach {ingrediente ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "- $ingrediente",
                        modifier = Modifier.weight(1f)
                    )
                    TextButton(onClick = { ingredientes.remove(ingrediente)}) {
                        Text("Eliminar")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Preparación",
                style = MaterialTheme.typography.titleLarge
            )

            OutlinedTextField(
                value = nuevoPaso,
                onValueChange = { nuevoPaso = it },
                label = { Text("Nuevo paso") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = {
                    if (nuevoPaso.isNotBlank()) {
                        pasos.add(nuevoPaso)
                        nuevoPaso = ""
                    }
                }
            ) {
                Text("Añadir paso")
            }

            //"forEachIndexed permite numerar automáticamente los pasos"
            pasos.forEachIndexed { index, paso ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${index + 1}. $paso",
                        modifier = Modifier.weight(1f)
                    )
                    TextButton(onClick = {pasos.remove(paso)}
                    ) {
                        Text("Eliminar")
                    }
                }
            }
        }
    }
}
