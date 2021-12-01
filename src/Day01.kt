fun main(args: Array<String>) {
    fun part1(input: List<Int>): Int {
        var count = 0
        for (i in 1 until input.size)
            if (input[i] > input[i - 1])
                count++
        return count
    }

    fun part2(input: List<Int>): Int {
        var count = 0
        for (i in 0..input.size - 4) {
            val windowA = input.drop(i).take(3)
            val windowB = input.drop(i+1).take(3)
            //println("Window A: $windowA -> ${windowA.sum()};  Window B: $windowB -> ${windowB.sum()}")

            if (windowA.sum() < windowB.sum())
                count++
        }

        return count
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test").map { it.toInt() }
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01").map { it.toInt() }
    println(part1(input))
    println(part2(input))
}
