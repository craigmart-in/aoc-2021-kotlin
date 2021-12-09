import kotlin.math.abs

fun main() {
    fun parseInput(input: List<String>): List<Int> {
        return input[0].split(",").map { it.toInt() }
    }

    fun part1(input: List<Int>): Int {
        var fuelUsedByPosition = mutableMapOf<Int, Int>()

        val crabsByPosition = input.groupBy { it }
        println(crabsByPosition)
        crabsByPosition.forEach { horizontalPosition ->
            var fuelUsed = 0
            crabsByPosition.forEach {
                fuelUsed += abs(horizontalPosition.key - it.key) * it.value.size
            }
            fuelUsedByPosition[horizontalPosition.key] = fuelUsed
        }

        var leastUsedFuel = Int.MAX_VALUE

        fuelUsedByPosition.forEach {
            if (it.value < leastUsedFuel) {
                leastUsedFuel = it.value
            }
        }

        return leastUsedFuel
    }

    fun part2(input: List<Int>): Int {
        return 1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput("Day07_test"))
    check(part1(testInput) == 37)

    val input = parseInput(readInput("Day07"))
    println(part1(input))
    println(part2(input))
}
