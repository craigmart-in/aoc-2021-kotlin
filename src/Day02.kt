fun main(args: Array<String>) {
    fun part1(input: List<String>): Int {
        var horizontalPosition = 0
        var depth = 0

        var commands = input.map {
            val split = it.split(' ')
            Pair(split[0], split[1].toInt())
        }

        for (command in commands) {
            //println("${command.first} ${command.second}")
            when (command.first) {
                "forward" -> horizontalPosition += command.second
                "down" -> depth += command.second
                "up" -> depth -= command.second
            }
        }

        //println("Horizontal: $horizontalPosition; Depth: $depth")

        return horizontalPosition * depth
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    //check(part2(testInput) == 1)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
