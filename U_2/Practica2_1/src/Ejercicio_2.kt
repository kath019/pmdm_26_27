
fun  main(){
    //2.a prueba
    println("Ejercicio 2.a")
    println(clasificarNota(3.3))
    println(clasificarNota(5.0))
    println(clasificarNota(7.8))
    println(clasificarNota(9.5))
    println(clasificarNota(-1.0))

    //2.b
    println("Números divisibles del 1 al 100 divisibles entre 3 y 5:")
    for (i in 1..100){
        if ((i%3 == 0) && (i%5 == 0)){
            println(i)
        }
    }
    println()

    //2.c
    println("\"FizzBuzz\"")
    for (i in 1..30){
        if((i%3 == 0) && (i % 5 == 0)){
            println("FizzBuzz")
        }else if (i%3 == 0){
            println("Fizz")
        } else if (i%5 == 0){
            println("Buzz")
        } else {
            println(i)
        }
    }
}

//2.a
fun clasificarNota(nota: Double):String {

    return when {
        nota >= 0 && nota < 5 -> "Suspenso"
        nota >=5 && nota < 6 -> "Aprobado"
        nota >=6 && nota < 7 -> "Bien"
        nota >=7 && nota < 9 -> "Notable"
        nota >= 8 && nota <= 10 -> "Sobresaliente"
        else -> "Nota no válida"
    }
}


