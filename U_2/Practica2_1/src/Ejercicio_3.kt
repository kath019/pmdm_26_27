fun main (){
    println("Usuario 2 con el nombre en mayúsculas:")
    val usuario2 = buscarUsuario(2)
    println(usuario2?.uppercase())

    println("Usuario con id: 99")
    val usuario99=buscarUsuario(99)
    println(usuario99 ?: "Desconocido")

    println("Busque al usuario con id = 1 y usa ?.let { } ")
    val usuario1=buscarUsuario(1)
    usuario1?.let {
        println("Usuario encontrado: $it")
    }
}

fun buscarUsuario(id: Int): String? {
    val usuarios = mapOf(1 to "Ana", 2 to "Luis", 3 to "María")
    return usuarios[id]
}