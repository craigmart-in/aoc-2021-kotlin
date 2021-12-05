fun main() {

    val winningNumberIndexes = arrayOf(
        listOf(0,1,2,3,4),
        listOf(5,6,7,8,9),
        listOf(10,11,12,13,14),
        listOf(15,16,17,18,19),
        listOf(20,21,22,23,24),
        listOf(0,5,10,15,20),
        listOf(1,6,11,16,21),
        listOf(2,7,12,17,22),
        listOf(3,8,13,18,23),
        listOf(4,9,14,19,24)
    )

    fun getDrawnNumbers(input: List<String>): List<Int> {
        val drawnNumbers = input[0].split(",").map { it.toInt() }
        //println("Drawn Numbers: $drawnNumbers")
        return drawnNumbers
    }

    fun getBoards(input: List<String>): MutableList<List<Int>> {
        val boards: MutableList<List<Int>> = mutableListOf()
        for (row in 2..input.size step 6) {
            //println("Board:")
            val board: MutableList<Int> = mutableListOf()
            for(boardRow in 0..4) {
                //println(input[row+boardRow])
                board.addAll(input[row+boardRow].split(" ").filter { it != "" }.map { it.toInt() })
            }
            //println("Board array: $board")

            boards.add(board)
        }
        //boards.forEachIndexed { index, list ->  println("Board $index: $list")}
        return boards
    }

    fun calculateScore(board: List<Int>, winningIndexes: MutableList<Int>, lastWinningNumber: Int): Int {
        val losingNumbers = board.filterIndexed { index, s -> !winningIndexes.contains(index) }

        //println ("Losing Numbers: $losingNumbers; Sum: ${losingNumbers.sum()}")

        return losingNumbers.sum() * lastWinningNumber
    }

    fun part1(drawnNumbers: List<Int>, boards: MutableList<List<Int>>): Int {
        val winningIndexesByBoard = List<MutableList<Int>>(boards.size) { mutableListOf() }
        var lastWinningNumber = -1
        var winningBoardIndex = -1

        // Loop through all the drawn numbers
        // Then loop through all the boards
        loop@ for (drawnNumber in drawnNumbers) {
            for ((index, board) in boards.withIndex()) {
                // Check if the board contains that drawn number
                val winningIndex = board.indexOf(drawnNumber)
                if (winningIndex == -1) continue

                winningIndexesByBoard[index].add(winningIndex)

                // Check if the board is a winner by seeing if the winning indexes matches any of the winning conditions
                for (winningIndexes in winningNumberIndexes) {
                    if (winningIndexesByBoard[index].containsAll(winningIndexes)) {
                        lastWinningNumber = drawnNumber
                        winningBoardIndex = index
                        break@loop
                    }
                }
            }
        }

        //println("Winning Board: $winningBoardIndex; Last Winning Number: $lastWinningNumber")

        return calculateScore(boards[winningBoardIndex], winningIndexesByBoard[winningBoardIndex], lastWinningNumber)
    }

    fun part2(drawnNumbers: List<Int>, boards: MutableList<List<Int>>): Int {
        val winningIndexesByBoard = List<MutableList<Int>>(boards.size) { mutableListOf() }
        val winningBoardIndexes = mutableListOf<Int>()
        var lastWinningNumber = -1

        // Loop through all the drawn numbers
        // Then loop through all the boards
        loop@ for (drawnNumber in drawnNumbers) {
            for ((index, board) in boards.withIndex()) {
                // Board already won so ignore it
                if (winningBoardIndexes.contains(index)) continue

                // Check if the board contains that drawn number
                val winningIndex = board.indexOf(drawnNumber)
                if (winningIndex == -1) continue

                winningIndexesByBoard[index].add(winningIndex)

                // Check if the board is a winner by seeing if the winning indexes matches any of the winning conditions
                for (winningIndexes in winningNumberIndexes) {
                    if (winningIndexesByBoard[index].containsAll(winningIndexes)) {
                        lastWinningNumber = drawnNumber
                        winningBoardIndexes.add(index)
                    }
                }
            }
        }

        //println("Winning Board: $winningBoardIndex; Last Winning Number: $lastWinningNumber")

        return calculateScore(boards[winningBoardIndexes.last()], winningIndexesByBoard[winningBoardIndexes.last()], lastWinningNumber)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    var drawnNumbers = getDrawnNumbers(testInput)
    var boards = getBoards(testInput)
    check(part1(drawnNumbers, boards) == 4512)

    val input = readInput("Day04")
    drawnNumbers = getDrawnNumbers(input)
    boards = getBoards(input)
    println(part1(drawnNumbers, boards))
    println(part2(drawnNumbers, boards))
}
