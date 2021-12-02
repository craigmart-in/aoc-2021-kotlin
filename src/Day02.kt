fun main(args: Array<String>) {
    fun getCommands(input: List<String>) : List<Command> {
        return input.map {
            val split = it.split(' ')
            Command(split[0], split[1].toInt())
        }
    }

    fun part1(input: List<String>): Int {
        var horizontalPosition = 0
        var depth = 0

        var commands = getCommands(input)

        for (command in commands) {
            //println("${command.first} ${command.second}")
            when (command.direction) {
                "forward" -> horizontalPosition += command.value
                "down" -> depth += command.value
                "up" -> depth -= command.value
            }
        }

        //println("Horizontal: $horizontalPosition; Depth: $depth")

        return horizontalPosition * depth
    }

    fun part2(input: List<String>): Int {
        var horizontalPosition = 0
        var depth = 0
        var aim = 0

        var commands = getCommands(input)

        for (command in commands) {
            //println("${command.first} ${command.second}")
            when (command.direction) {
                "forward" -> {
                    horizontalPosition += command.value
                    depth += aim * command.value
                }
                "down" -> {
                    aim += command.value
                }
                "up" -> {
                    aim -= command.value
                }
            }
        }

        //println("Horizontal: $horizontalPosition; Depth: $depth")

        return horizontalPosition * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

data class Command(var direction: String, var value: Int)