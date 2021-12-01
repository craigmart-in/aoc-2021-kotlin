fun main(args: Array<String>) {
    fun part1(input: List<String>): Int {
        val convertedInput = input.map { it.toInt() }
        var count = 0
        for (i in 1 until convertedInput.size)
            if (convertedInput[i] > convertedInput[i - 1])
                count++
        return count
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
