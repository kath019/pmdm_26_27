package com.example.multipantalla_con_navegacion.model

val recetasIniciales = listOf(
    Receta(
        id = 1,
        nombre = "Tortilla española",
        categoria = "Principal",
        tiempoMinutos = 30,
        dificultad = "Media",
        ingredientes =  listOf(
            "6 huevos",
            "4 patatas",
            "1 cebolla",
            "Aceite de oliva",
            "Sal"
        ),

        pasos = listOf(
            "Pelar y cortar las patatas en láminas finas",
            "Freír las patatas con la cebolla",
            "Batir los huevos con sal",
            "Mezclar los huevos con las patatas",
            "Cuajar la tortilla por ambos lados"
        ),
        imagenEmoji = "🥚"
    ),
    Receta(
        id = 2,
        nombre = "Gazpacho andaluz",
        categoria = "Entrante",
        tiempoMinutos = 15,
        dificultad = "Fácil",
        ingredientes = listOf(
            "1 kg de tomates",
            "1 pepino",
            "1 pimiento verde",
            "1 diente de ajo",
            "Aceite de oliva",
            "Vinagre",
            "Sal"
        ),
        pasos = listOf(
            "Lavar y trocear las verduras",
            "Introducir todos los ingredientes en una batidora",
            "Añadir aceite, vinagre y sal",
            "Triturar hasta conseguir una mezcla homogénea",
            "Dejar enfriar en la nevera"
        ),
        imagenEmoji = "🍅"
    ),

    Receta(
        id = 3,
        nombre = "Paella valenciana",
        categoria = "Principal",
        tiempoMinutos = 60,
        dificultad = "Difícil",
        ingredientes = listOf(
            "400 g de arroz",
            "Pollo",
            "Conejo",
            "Judías verdes",
            "Tomate triturado",
            "Azafrán",
            "Agua",
            "Aceite de oliva",
            "Sal"
        ),
        pasos = listOf(
            "Dorar el pollo y el conejo",
            "Añadir las judías verdes",
            "Incorporar el tomate triturado",
            "Añadir agua y azafrán",
            "Incorporar el arroz",
            "Cocinar hasta que el arroz esté en su punto"
        ),
        imagenEmoji = "🥘"
    ),

    Receta(
        id = 4,
        nombre = "Flan casero",
        categoria = "Postre",
        tiempoMinutos = 45,
        dificultad = "Media",
        ingredientes = listOf(
            "500 ml de leche",
            "4 huevos",
            "100 g de azúcar",
            "Caramelo líquido"
        ),
        pasos = listOf(
            "Preparar el caramelo",
            "Batir los huevos con el azúcar",
            "Añadir la leche y mezclar",
            "Verter la mezcla en un molde",
            "Cocinar al baño maría",
            "Dejar enfriar antes de servir"
        ),
        imagenEmoji = "🍮"
    ),

    Receta(
        id = 5,
        nombre = "Limonada natural",
        categoria = "Bebida",
        tiempoMinutos = 10,
        dificultad = "Fácil",
        ingredientes = listOf(
            "4 limones",
            "1 litro de agua",
            "Azúcar",
            "Hielo"
        ),
        pasos = listOf(
            "Exprimir los limones",
            "Mezclar el zumo con el agua",
            "Añadir azúcar al gusto",
            "Remover bien",
            "Servir con hielo"
        ),
        imagenEmoji = "🍋"
    ),

    Receta(
        id = 6,
        nombre = "Arroz con leche",
        categoria = "Postre",
        tiempoMinutos = 40,
        dificultad = "Fácil",
        ingredientes = listOf(
            "1 litro de leche",
            "100 g de arroz",
            "100 g de azúcar",
            "1 rama de canela",
            "Cáscara de limón"
        ),
        pasos = listOf(
            "Calentar la leche con la canela y el limón",
            "Añadir el arroz",
            "Cocinar a fuego lento removiendo",
            "Incorporar el azúcar",
            "Retirar la canela y el limón",
            "Dejar enfriar y servir con canela"
        ),
        imagenEmoji = "🍚"
    )
)