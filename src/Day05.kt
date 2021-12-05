import kotlin.math.abs
import kotlin.math.max

fun main() {
    fun parseInput(input: List<String>): List<Pair<Point, Point>> {
        return input.map { it ->
            val coordinates = it.split(" -> ")
            var points = coordinates[0].split(",").map { it.toInt() }

            val point1 = Point(points[0], points[1])

            points = coordinates[1].split(",").map { it.toInt() }

            val point2 = Point(points[0], points[1])

            Pair(point1, point2)
        }
    }

    fun getVentCount(point1: Point, point2: Point): Int {
        return max(abs(point1.x - point2.x), abs(point1.y - point2.y))
    }

    fun getChange(start: Int, end: Int): Int {
        return end.compareTo(start)
    }

    fun getDangerousVentCount(input: List<String>, ignoreDiagonal: Boolean): Int {
        val ventCounts: MutableMap<Point, Int> = mutableMapOf()
        val coordinates = parseInput(input)

        for(coordinate in coordinates) {
            val point1 = coordinate.first
            val point2 = coordinate.second

            val ventLocations: MutableList<Point> = mutableListOf()

            var count = getVentCount(point1, point2)
            var dx = getChange(point1.x, point2.x)
            var dy = getChange(point1.y, point2.y)

            //print("(${point1.x}, ${point1.y}) -> (${point2.x}, ${point2.y}); Count: $count; dx: $dx; dy: $dy;  ")

            if (ignoreDiagonal && dx != 0 && dy != 0) {
                //println("Skipping because of diagonal")
                continue
            }

            for (i in 0..count) {
                ventLocations.add(Point(point1.x + (dx * i), point1.y + (dy * i)))
            }

            //println(ventLocations)

            ventLocations.forEach { vent ->
                if (ventCounts.contains(vent)) {
                    ventCounts[vent] = ventCounts[vent]!!.plus(1)
                }
                else {
                    ventCounts[vent] = 1
                }
            }
        }

        return ventCounts.count { it.value > 1 }
    }

    fun part1(input: List<String>): Int {
        return getDangerousVentCount(input, true)
    }

    fun part2(input: List<String>): Int {
        return getDangerousVentCount(input, false)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}

data class Point(val x: Int, val y: Int)