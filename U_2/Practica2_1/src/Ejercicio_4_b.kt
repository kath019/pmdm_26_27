
// 1. Invierte la cadena y la convierte a mayúsculas
fun String.alRevesConMayusculas(): String {
    return this.reversed().uppercase()
}

// 2. Devuelve el segundo número más grande distinto, o null
fun List<Int>.segundoMayor(): Int? {
    val numerosOrdenados = this.distinct().sortedDescending()

    return numerosOrdenados.getOrNull(1)
}

// 3. Comprueba si un número es primo
fun Int.esPrimo(): Boolean {
    if (this < 2) {
        return false
    }

    for (divisor in 2 until this) {
        if (this % divisor == 0) {
            return false
        }
    }

    return true
}


fun main() {
    // Prueba 1
    println("Kotlin".alRevesConMayusculas())

    // Prueba 2
    println(listOf(3, 7, 1, 9, 5).segundoMayor())
    println(listOf(5, 5, 5).segundoMayor())

    // Prueba 3: imprimir los números primos del 1 al 50
    for (numero in 1..50) {
        if (numero.esPrimo()) {
            println(numero)
        }
    }
}


