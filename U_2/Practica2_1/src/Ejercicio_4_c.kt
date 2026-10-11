data class Configuracion(
    var idioma: String = "es",
    var modoOscuro: Boolean = false,
    var tamanoFuente: Int = 14,
    var notificaciones: Boolean = true
)

fun main() {

    // 1. Configurar y modificar sus propiedades con apply
    val configuracion = Configuracion().apply {
        idioma = "en"
        modoOscuro = true
        tamanoFuente = 18
        notificaciones = false
    }

    // 2. Usar also para imprimir la configuración antes de utilizarla
    configuracion.also {
        println("Configuración inicial: $it")
    }

    // 3. Let para transformar la configuración en un String
    val descripcion = configuracion.let {
        "Idioma: ${it.idioma}, modo oscuro: ${it.modoOscuro}, " +
                "tamaño de fuente: ${it.tamanoFuente}, " +
                "notificaciones: ${it.notificaciones}"
    }

    println(descripcion)

    // 4. Run para un resumen de la configuración
    val resumen = configuracion.run {
        "Resumen: idioma $idioma, fuente de tamaño $tamanoFuente, " +
                "modo oscuro ${if (modoOscuro) "activado" else "desactivado"}, " +
                "notificaciones ${if (notificaciones) "activadas" else "desactivadas"}."
    }

    println(resumen)
}

