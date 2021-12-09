import kotlin.math.abs

fun main() {
    fun parseInput(input: List<String>): List<Int> {
        return input[0].split(",").map { it.toInt() }
    }

    fun part1(input: List<Int>): Int {
        var fuelUsedByPosition = mutableMapOf<Int, Int>()

        val crabsByPosition = input.groupBy { it }
        crabsByPosition.forEach { horizontalPosition ->
            var fuelUsed = 0
            crabsByPosition.forEach {
                fuelUsed += abs(horizontalPosition.key - it.key) * it.value.size
            }
            fuelUsedByPosition[horizontalPosition.key] = fuelUsed
        }

        return fuelUsedByPosition.map { it.value }.minOf { it }
    }

    fun part2(input: List<Int>): Int {
        var fuelUsedByPosition = mutableMapOf<Int, Int>()

        val crabsByPosition = input.groupBy { it }
        val minPosition = crabsByPosition.map { it.key }.minOf { it }
        val maxPosition = crabsByPosition.map { it.key }.maxOf { it }
        (minPosition..maxPosition).forEach { horizontalPosition ->
            var fuelUsed = 0
            crabsByPosition.forEach {
                fuelUsed += (abs(horizontalPosition - it.key) downTo 1).sum() * it.value.size
            }
            fuelUsedByPosition[horizontalPosition] = fuelUsed
        }

        println(fuelUsedByPosition)

        return fuelUsedByPosition.map { it.value }.minOf { it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput("Day07_test"))
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = parseInput(readInput("Day07"))
    println(part1(input))
    println(part2(input))
}
