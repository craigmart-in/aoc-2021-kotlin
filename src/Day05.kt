fun main() {
    fun part1(input: List<String>): Int {
        val ventCounts: MutableMap<Point, Int> = mutableMapOf()

        input.forEach { it ->
            val coordinates = it.split(" -> ")
            var points = coordinates[0].split(",").map { it.toInt() }

            val point1 = Point(points[0], points[1])

            points = coordinates[1].split(",").map { it.toInt() }

            val point2 = Point(points[0], points[1])

            val ventLocations: MutableList<Point> = mutableListOf()

            //print("$it: ")
            if (point1.x == point2.x) {
                //println("Horizontal")
                if (point1.y < point2.y) {
                    for (index in point1.y..point2.y) {
                        ventLocations.add(Point(point1.x, index))
                    }
                }
                else {
                    for (index in point2.y..point1.y) {
                        ventLocations.add(Point(point1.x, index))
                    }
                }
            }
            else if (point1.y == point2.y) {
                //println("Vertical")
                if (point1.x < point2.x) {
                    for (index in point1.x..point2.x) {
                        ventLocations.add(Point(index, point1.y))
                    }
                }
                else {
                    for (index in point2.x..point1.x) {
                        ventLocations.add(Point(index, point1.y))
                    }
                }
            }

            //print(line)
            //println()

            ventLocations.forEach { vent ->
                if (ventCounts.contains(vent)) {
                    ventCounts[vent] = ventCounts[vent]!!.plus(1)
                }
                else {
                    ventCounts[vent] = 1
                }
            }
        }
        //println(diagram)
        return ventCounts.count { it.value > 1 }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}

data class Point(val x: Int, val y: Int)