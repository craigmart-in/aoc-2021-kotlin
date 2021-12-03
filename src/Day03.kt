fun main() {
    fun getNumberOfOnesByIndex(input: List<String>): IntArray {
        val bitsLength = input[0].length
        var numberOfOnesByIndex = IntArray(bitsLength)

        for (bits in input) {
            //println(bits)
            for ((index, bit) in bits.withIndex()) {
                numberOfOnesByIndex[index] = numberOfOnesByIndex[index] + bit.toString().toInt()
            }
        }

        return numberOfOnesByIndex
    }

    fun getGammaRate(numberOfOnesByIndex: IntArray, halfSize: Float): String {
        var gammaRate = ""

        for (count in numberOfOnesByIndex) {
            if (count >= halfSize) {
                gammaRate += "1"
            }
            else {
                gammaRate += "0"
            }
        }

        return gammaRate
    }

    fun getEpsilonRate(gammaRate: String): String {
        return gammaRate.map { if (it == '1') '0' else '1' }.joinToString(separator = "")
    }

    fun getNumbersMatchingCommonBitInCurrentBitPosition(bitIndex: Int, commonBit: Char, input: List<String>): List<String> {
        return input.filter { it[bitIndex] == commonBit }
    }

    fun part1(input: List<String>): Int {
        var numberOfOnesByIndex = getNumberOfOnesByIndex(input)

        var gammaRate = getGammaRate(numberOfOnesByIndex, input.size / 2f)
        var epsilonRate = getEpsilonRate(gammaRate)

        val powerConsumption = gammaRate.toInt(2) * epsilonRate.toInt(2)

        //println("Gamma Rate: $gammaRate; Epsilon Rate: $epsilonRate; Power Consumption: $powerConsumption")

        return powerConsumption
    }

    fun part2(input: List<String>): Int {
        var tempInput = input;

        for (bitIndex in 0..input[0].length) {
            var numberOfOnesByIndex = getNumberOfOnesByIndex(tempInput)
            var gammaRate = getGammaRate(numberOfOnesByIndex, tempInput.size / 2f)
            //println("${numberOfOnesByIndex.joinToString()}; Gamma Rate: $gammaRate; Input: ${tempInput.joinToString()}")
            tempInput = getNumbersMatchingCommonBitInCurrentBitPosition(bitIndex, gammaRate[bitIndex], tempInput)
            //println("Pruned List: ${tempInput.joinToString()}")

            if (tempInput.size == 1) {
                break;
            }
        }

        var oxygenGeneratorRating = tempInput[0];

        tempInput = input;

        for (bitIndex in 0..input[0].length) {
            var numberOfOnesByIndex = getNumberOfOnesByIndex(tempInput)
            var epsilonRate = getEpsilonRate(getGammaRate(numberOfOnesByIndex, tempInput.size / 2f))
            tempInput = getNumbersMatchingCommonBitInCurrentBitPosition(bitIndex, epsilonRate[bitIndex], tempInput)

            if (tempInput.size == 1) {
                break;
            }
        }

        var co2GeneratorRating = tempInput[0];

        //println("Oxygen: $oxygenGeneratorRating; CO2: $co2GeneratorRating;")

        return oxygenGeneratorRating.toInt(2) * co2GeneratorRating.toInt(2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}