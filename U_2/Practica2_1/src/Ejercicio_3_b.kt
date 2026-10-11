
fun main() {
    data class Direccion(val ciudad: String?, val codigoPostal: String?)
    data class Persona(val nombre: String, val direccion: Direccion?)

    val personas = listOf(
        Persona("Ana", Direccion("Madrid", "28001")),
        Persona("Luis", null),
        Persona("María", Direccion(null, "08001")),
        Persona("Carlos", Direccion("Barcelona", null))
    )

    for(persona in personas) {
        val direccion =persona.direccion

        if(direccion == null) {
            println("${persona.nombre} -> dirección desconocida")
        } else{
            val ciudad = direccion.ciudad?:"Ciudad desconocida"
            val cp = direccion.codigoPostal?:"CP desconocido"

            println("${persona.nombre} vive en $ciudad ($cp)")
        }
    }

}
