println("Start")
val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

val input = readInput("day4.txt").readText()

val validNum = input.split("\n\n")
    .asSequence()
    .map { passport ->
        val fullString = passport.replace("\n", " ")
        return@map if (requiredFields.all {
                fullString.contains(it)
            }) {
            fullString
        } else {
            null
        }
    }
    .filter { it != null && Passport.fromString(it).isValid() }
    .toList()
    .size

println("$validNum valid passports")


data class Passport(
    val byr: Int,
    val iyr: Int,
    val eyr: Int,
    val hgt: String,
    val hcl: String,
    val ecl: String,
    val pid: String,
    val cid: Int?
) {

    private val eyeColors = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

    fun isValid(): Boolean {
        return byr in 1920..2002 &&
                iyr in 2010..2020 &&
                eyr in 2020..2030 &&
                isValidHeight() &&
                hcl.matches(Regex("#[0-9a-f]{6}")) &&
                ecl in eyeColors &&
                pid.length == 9

    }

    private fun isValidHeight(): Boolean {
        return when {
            hgt.matches(Regex("\\d{3}cm")) -> hgt.substring(0, 3).toInt() in 150..193
            hgt.matches(Regex("\\d{2}in")) -> hgt.substring(0, 2).toInt() in 59..76
            else                          -> false
        }
    }

    companion object {
        fun fromString(str: String): Passport {
            val propMap = str.trim().split(" ").map {
                val (key, value) = it.split(":")
                return@map (key to value)
            }.toMap()

            return Passport(
                propMap["byr"]!!.toInt(),
                propMap["iyr"]!!.toInt(),
                propMap["eyr"]!!.toInt(),
                propMap["hgt"]!!,
                propMap["hcl"]!!,
                propMap["ecl"]!!,
                propMap["pid"]!!,
                propMap["cid"]?.toInt(),
            )
        }
    }
}
