fun main(){

    println(formatearSaludo("Ana"))
    println(formatearSaludo("Luis", tratamiento = "Dr."))
    println(formatearSaludo("María", tratamiento = "Profe", entusiasta =true))
}

fun formatearSaludo(
    nombre: String,
    tratamiento: String = "Sr./Sra.",
    entusiasta: Boolean = false
): String {
    var saludo: String = ""
    if(entusiasta == true) {
        saludo = "¡¡¡Hola, $tratamiento $nombre !!!"
    } else if (tratamiento == "Dr.") {
        saludo = "\"Hola, $tratamiento $nombre\""
    } else {
        saludo="Hola, Sr./Sra. $nombre"
    }
    return saludo
}