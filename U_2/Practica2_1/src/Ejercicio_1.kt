/*
Una constante PI de tipo Double con valor 3.14159.
Una variable inmutable nombre con tu nombre.
Una variable mutable edad con tu edad.
Una variable esEstudiante de tipo Boolean.
Incrementa edad en 1 y muestra el nuevo valor.
Muestra un mensaje usando string template: "Me llamo {nombre}, tengo {edad} años".
 */

fun main(){
    val  pi: Double = 3.14159
    println("Valor de PI: $pi")

    val nombre = "Katlhyn"
    println("Valor de nombre: $nombre")

    var edad = 21
    println("Valor de edad antes de modificarlo: $edad")

    val esEstudiante: Boolean = true
    println("Valor de Boolean: $esEstudiante")

    edad += 1
    println("Valor de Edad modificado: $edad")
    println("Me llamo $nombre, tengo $edad años")
}