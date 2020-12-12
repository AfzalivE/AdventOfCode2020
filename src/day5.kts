println("Start")

val input = readInput("day5.txt").readLines()

val allRows = 0..127
val allCols = 0..7
var allSeats = listOf<Int>()
for (i in allRows) {
    for (j in allCols) {
        allSeats = allSeats + (i * 8 + j)
    }
}

val seats = input.map {
    var rows = IntRange(0, 127)
    var columns = IntRange(0, 7)

    it.asSequence().forEach {
        when (it) {
            'F' -> rows = lowerHalf(rows.first, rows.last)
            'B' -> rows = upperHalf(rows.first, rows.last)
            'R' -> columns = upperHalf(columns.first, columns.last)
            'L' -> columns = lowerHalf(columns.first, columns.last)

        }
    }
    val seatId = rows.first * 8 + columns.first
    // println("Row: ${rows.first}, column: ${columns.first}, seat ID: $seatId")
    seatId
}

val highestSeatId = seats.maxOrNull()
println("Highest seat ID: $highestSeatId")

val absentSeats = allSeats.filterNot { seats.contains(it) }
val mySeat = absentSeats.filterIndexed { index, i ->
    index > 0 && absentSeats[index - 1] != i - 1 && absentSeats[index + 1] != i + 1
}
println("My seat is ${mySeat[0]}")

fun lowerHalf(start: Int, end: Int) = IntRange(start, start + (end - start) / 2)

fun upperHalf(start: Int, end: Int) = IntRange(start + ((end - start + 1) / 2), end)