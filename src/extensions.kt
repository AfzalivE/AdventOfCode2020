import java.io.File

fun Any.readInput(filename: String): File {
    return File(javaClass.getResource(filename).toURI())
}