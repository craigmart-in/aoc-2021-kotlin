import com.sun.xml.internal.fastinfoset.util.StringArray

fun main() {
    fun part1(input: List<String>): Int {
        val uniqueDigitCounts = listOf(2, 4, 3, 7)
        var count = 0

        for (line in input) {
            val outputs = line.split(" | ")[1].split(" ").map { it.count() }.groupBy { it }
            println(outputs)
            uniqueDigitCounts.forEach {
                count += outputs.getOrDefault(it, listOf()).size
            }
        }

        println(count)

        return count
    }

    fun part2(input: List<String>): Int {
        /*
        7 segment indexes
          00
         1  2
         1  2
          33
         4  5
         4  5
          66
         */

        var sum = 0;

        for (line in input) {
            var sevenSegment = Array(7) {'z'}
            var numbers = Array(10) {"z"}

            val splitLine = line.split(" | ")
            val patterns = splitLine[0].split(" ")
            val outputs = splitLine[1].split(" ")

            println()
            println(line)

            // Find the 1
            numbers[1] = patterns.find { it.count() == 2 }.toString()

            // Find the 4
            numbers[4] = patterns.find { it.count() == 4}.toString()

            // Find the 7
            numbers[7] = patterns.find { it.count() == 3 }.toString()

            // Find the 8
            numbers[8] = patterns.find { it.count() == 7 }.toString()

            // Find index 0
            sevenSegment[0] = numbers[7].toCharArray().subtract(numbers[1].toCharArray().toSet()).toCharArray().first()

            val lettersNotInOne = numbers[8].toCharArray().subtract(numbers[1].toCharArray().toSet())
            // Find numbers with count of 6
            val numbersWithSix = patterns.filter { it.count() == 6 }

            // Get the 5th index char
            numbersWithSix.forEach {
                val temp = it.toCharArray().subtract(lettersNotInOne.toCharArray().toSet())
                if (temp.size == 1) {
                    sevenSegment[5] = temp.first()
                    numbers[6] = it
                }
            }

            // Get the 2nd index char
            sevenSegment[2] = numbers[1].toCharArray().subtract(setOf(sevenSegment[5])).toCharArray().first()

            // Get 0 and 9
            val zeroOrNine = numbersWithSix.filter { it != numbers[6] }
            zeroOrNine.forEach {
                val temp = numbers[4].toCharArray().subtract(it.toCharArray().toSet())
                if (temp.isEmpty()) {
                    numbers[9] = it
                } else {
                    numbers[0] = it
                }
            }

            // Get index 3
            sevenSegment[3] = numbers[8].toCharArray().subtract(numbers[0].toCharArray().toSet()).toCharArray().first()

            // Get index 4
            sevenSegment[4] = numbers[8].toCharArray().subtract(numbers[9].toCharArray().toSet()).toCharArray().first()

            // Get index 1
            sevenSegment[1] = numbers[4].toCharArray().subtract(setOf(sevenSegment[2], sevenSegment[3], sevenSegment[5])).toCharArray().first()

            // Get index 6
            sevenSegment[6] = numbers[8].toCharArray().subtract(sevenSegment.take(6).toSet()).toCharArray().first()

            // Get 2
            numbers[2] = patterns.first {
                it.count() == 5 && it.toCharArray().subtract(
                    setOf(
                        sevenSegment[0],
                        sevenSegment[2],
                        sevenSegment[3],
                        sevenSegment[4],
                        sevenSegment[6]
                    )
                ).isEmpty()
            }

            // Get 3
            numbers[3] = patterns.first {
                it.count() == 5 && it.toCharArray().subtract(
                    setOf(
                        sevenSegment[0],
                        sevenSegment[2],
                        sevenSegment[3],
                        sevenSegment[5],
                        sevenSegment[6]
                    )
                ).isEmpty()
            }

            // Get 5
            numbers[5] = patterns.first {
                it.count() == 5 && it.toCharArray().subtract(
                    setOf(
                        sevenSegment[0],
                        sevenSegment[1],
                        sevenSegment[3],
                        sevenSegment[5],
                        sevenSegment[6]
                    )
                ).isEmpty()
            }

            print("Numbers: ")
            numbers.forEachIndexed { index, it -> print("$index:$it ") }
            println()
            print("Seven Segment Letters by Index: ")
            sevenSegment.forEachIndexed { index, it -> print("$index:$it ") }
            println()

            var outputNumber = ""

            for(output in outputs) {
                numbers.forEachIndexed { index, s ->
                    if (output.toSortedSet() == s.toSortedSet()) {
                        outputNumber += index.toString()
                    }
                }
            }

            println("Output: $outputNumber")

            sum += outputNumber.toInt()
        }

        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
