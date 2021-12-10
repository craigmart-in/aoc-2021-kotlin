fun main() {
    fun part1(input: List<String>): Int {
        val uniqueDigitCounts = listOf(2, 4, 3, 7)
        var outputValues = mutableListOf<Int>()
        var count = 0

        for (line in input) {
            val outputs = line.split(" | ")[1].split(" ").map { it.count() }.groupBy { it }
            println(outputs)
            uniqueDigitCounts.forEach {
                count += outputs.getOrDefault(it, listOf()).size
            }
        }

        println(count)

        return count
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
