println("Start")

val input = readInput("day3.txt").readLines().map {
    it.repeat(500)
}
val slopes = listOf(
    Slope(1, 1),
    Slope(3, 1),
    Slope(5, 1),
    Slope(7, 1),
    Slope(1, 2)
)
val treesHit = slopes.map {
    val treesHit1 = treesHit(it)
    println("Hit $treesHit1 for $it")
    treesHit1
}

val product = treesHit.reduce { acc, i ->
    acc * i
}

println(product)

fun treesHit(slope: Slope): Int {
    var i = slope.right
    var row = slope.down
    var treesHit = 0

    while (row < input.size) {
        val currRow = input[row]
        if (currRow[i] == "#".single()) {
            treesHit += 1
        }
        i += slope.right
        row += slope.down
    }
    return treesHit
}

data class Slope(
    val right: Int,
    val down: Int
)