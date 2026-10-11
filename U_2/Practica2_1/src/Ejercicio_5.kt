data class Alumno(
    val nombre: String,
    val apellido: String,
    val edad: Int,
    val nota: Double,
    val curso: String
)

fun main() {
    val alumnos = listOf(
        Alumno("Ana", "García", 20, 8.5, "DAM"),
        Alumno("Luis", "Martínez", 22, 6.3, "DAW"),
        Alumno("María", "López", 19, 9.1, "DAM"),
        Alumno("Carlos", "Fernández", 21, 4.8, "DAM"),
        Alumno("Elena", "Rodríguez", 20, 7.5, "DAW"),
        Alumno("Pedro", "Sánchez", 23, 5.2, "DAM"),
        Alumno("Laura", "Díaz", 19, 8.8, "DAW"),
        Alumno("Jorge", "Ruiz", 21, 3.9, "DAW"),
        Alumno("Sofía", "Moreno", 20, 9.5, "DAM"),
        Alumno("Diego", "Álvarez", 22, 6.7, "DAM")
    )

    // 1. Aprobados
    val aprobados = alumnos.filter { it.nota >= 5 }.sortedByDescending { it.nota }.map { "${it.nombre} ${it.apellido}" }
    println("1. Aprobados: $aprobados")

    // 2. Media por curso
    val mediaPorCurso = alumnos.groupBy { it.curso }.mapValues { (_, lista) -> lista.map { it.nota }.average() }
    println("2. Media por curso: $mediaPorCurso")

    // 3. Mejor alumno por curso
    val mejorPorCurso = alumnos.groupBy { it.curso }.mapValues { (_, lista) -> lista.maxByOrNull { it.nota }!! }
    println("3. Mejor alumno por curso: $mejorPorCurso")

    // 4. Nombres por inicial
    val nombresPorInicial = alumnos.groupBy { it.nombre.first() }.mapValues { (_, lista) -> lista.map { it.nombre } }
    println("4. Nombres por inicial: $nombresPorInicial")

    // 5. Todos aprobados?
    val todosAprobados = alumnos.all { it.nota >= 5 }
    println("5. ¿Todos aprobados?: $todosAprobados")

    // 6. Menor de 20 con mejor nota
    val mejorMenorDe20 = alumnos.filter { it.edad < 20 }.maxByOrNull { it.nota }
    println("6. Mejor alumno menor de 20: $mejorMenorDe20")

    // 7. Ranking
    val ranking = alumnos.sortedByDescending { it.nota }.mapIndexed { index, alumno -> "${index + 1}. ${alumno.nombre} ${alumno.apellido} - ${alumno.nota}" }
    println("7. Ranking: $ranking")

    // 8. Estadísticas
    val notas = alumnos.map { it.nota }
    val estadisticas = "Mínima: ${notas.minOrNull()}, Máxima: ${notas.maxOrNull()}, Media: ${notas.average()}, Desviación típica: ${kotlin.math.sqrt(notas.map { (it - notas.average()) * (it - notas.average()) }.average())}"
    println("8. Estadísticas: $estadisticas")
}
