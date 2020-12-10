import java.io.File

fun Any.readInput(filename: String): List<String> {
    return File(javaClass.getResource(filename).toURI()).readLines()
}