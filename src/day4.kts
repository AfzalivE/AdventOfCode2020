println("Start")
val input = readInput("day4.txt").readText()
input.splitToSequence("\n\n").map {
    println(it.replace("\n", " "))
}