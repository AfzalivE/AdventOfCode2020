println("Start")
// val input = listOf(
//     "1-3 a: abcde",
//     "1-3 b: cdefg",
//     "2-9 c: ccccccccc"
// )

val input = readInput("day2.txt").readLines()

val validPasswords = findValid(input)
println("${validPasswords.size} valid passwords")
// println(validPasswords)

fun findValid(input: List<String>): List<Pair<Policy, String>> {
    return input.map {
        val (policyStr, password) = it.split(":")
        Pair(Policy.fromString(policyStr), password.trim())
    }.filter {
        it.first.matchesPart2(it.second)
    }
}

data class Policy(
    val min: Int,
    val max: Int,
    val letter: Char
) {
    fun matchesPart1(password: String): Boolean {
        val count = password.count {
            it == letter
        }
        return count in min..max
    }

    fun matchesPart2(password: String): Boolean {
        val minMatch = password[min - 1] == letter
        val maxMatch = password[max - 1] == letter
        return minMatch xor maxMatch
        // return (minMatch || maxMatch) && minMatch != maxMatch
    }

    companion object {
        fun fromString(string: String): Policy {
            val (range, letter) = string.split(" ")
            val (min, max) = range.split("-")
            return Policy(min.toInt(), max.toInt(), letter.single())
        }
    }
}