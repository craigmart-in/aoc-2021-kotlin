fun main() {
    fun getInitialLanternFish(input: String): MutableList<Int> {
        return input.split(",").map { it.toInt() }.toMutableList()
    }

    fun countFishes(fishes: MutableList<Int>, days: Int): Long {
        var fishAgeByCount = fishes
            .groupBy { it }
            .mapValues { it.value.size.toLong() }
            .toSortedMap()

        println("Initial state: $fishAgeByCount")

        for (day in 0..days) {
            val temp = mutableMapOf<Int, Long>().toSortedMap()
            for (age in 8 downTo 0) {
                if (age == 0) {
                    temp[8] = fishAgeByCount.getOrDefault(age, 0)
                    temp[6] = temp.getOrDefault(6, 0) + fishAgeByCount.getOrDefault(age, 0)
                }
                else {
                    temp[age - 1] = fishAgeByCount.getOrDefault(age, 0)
                }
            }

            fishAgeByCount = temp
            println("After day ${day + 1}: $fishAgeByCount")
        }

        var count = 0L
        fishAgeByCount.forEach{
            count += it.value
        }

        println("Sum: $count")

        return count
    }

    fun part1(fishes: MutableList<Int>): Long {
        return countFishes(fishes, 79)
    }

    fun part2(fishes: MutableList<Int>): Long {
        return countFishes(fishes, 255)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    val parsedTestInput = getInitialLanternFish(testInput[0])
    check(part1(parsedTestInput) == 5934L)
    check(part2(parsedTestInput) == 26984457539)

    val input = readInput("Day06")
    val parsedInput = getInitialLanternFish(input[0])
    println(part1(parsedInput))
    println(part2(parsedInput))
}