fun main() {
    fun getInitialLanternFish(input: String): MutableList<Int> {
        return input.split(",").map { it.toInt() }.toMutableList()
    }

    fun part1(fishes: MutableList<Int>): Int {
        println("Initial state: $fishes")
        for (day in 0..79) {
            val fishCount = fishes.size - 1
            for (index in 0..fishCount) {
                if (fishes[index] == 0) {
                    fishes[index] = 6
                    fishes.add(8)
                }
                else {
                    fishes[index] = fishes[index] - 1
                }
            }

            println("After day ${day + 1}: $fishes")
        }

        return fishes.size
    }

    fun part2(fishes: MutableList<Int>): Long {
        return 1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    val parsedTestInput = getInitialLanternFish(testInput[0])
    check(part1(parsedTestInput) == 5934)
    

    val input = readInput("Day06")
    val parsedInput = getInitialLanternFish(input[0])
    println(part1(parsedInput))
    println(part2(parsedInput))
}