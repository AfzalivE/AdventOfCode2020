println("Start")
// val input = listOf(1721, 979, 366, 299, 675, 1456)
val input = readInput("day1.txt").readLines().map { it.toInt() }

fun part1() {
    var i = 0
    var j = 1
    var foundPair = false
    while (!foundPair) {
        j += 1

        if (j == input.size - 2) {
            i += 1
            j = i + 1
        }

        val first = input[i]
        val second = input[j]
        if (sumTo2020(first, second)) {
            println("Found: $first, $second")
            println(first * second )
            foundPair = true
        }
    }
}

fun part2() {
    var i = 0
    var j = 1
    var k = j
    var foundPair = false
    while (!foundPair) {
        k += 1

        if (k == input.size - 1) {
            j += 1
            k = j + 1
        }

        if (j == input.size - 2) {
            i += 1
            j = i + 1
            k = j + 1
        }

        val first = input[i]
        val second = input[j]
        val third = input[k]
        if (sumTo2020(first, second, third)) {
            println("Found: $first, $second, $third")
            println(first * second * third)
            foundPair = true
        }
    }
}

part1()
part2()

fun sumTo2020(vararg numbers: Int): Boolean {
    return numbers.reduce { acc, i ->
        acc + i
    } == 2020
}