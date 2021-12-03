import java.util.*

fun main() {
    fun part1(input: List<String>): Int {
        val bitsLength = input[0].length
        var mostCommonBits = IntArray(bitsLength)

        for (bits in input) {
            //println(bits)
            for ((index, bit) in bits.withIndex()) {
                mostCommonBits[index] = mostCommonBits[index] + bit.toString().toInt()
            }
        }

        var gammaRate = ""
        var epsilonRate = ""

        for (count in mostCommonBits) {
            if (count > (input.size / 2)) {
                gammaRate += "1"
                epsilonRate += "0"
            }
            else {
                gammaRate += "0"
                epsilonRate += "1"
            }
        }

        val powerConsumption = gammaRate.toInt(2) * epsilonRate.toInt(2)

        //println("Gamma Rate: $gammaRate; Epsilon Rate: $epsilonRate; Power Consumption: $powerConsumption")

        return powerConsumption
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}