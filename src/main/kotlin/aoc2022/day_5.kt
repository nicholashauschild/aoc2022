package aoc2022

import java.util.Stack

fun main() {
    val input = readLines("input/day5.txt")

    var crateRows: List<String> = input.filter { it.trim().startsWith("[") }
    var crateStacks: List<Stack<Char>> = input
        .filter { it.startsWith(" 1") }[0]
        .split(" ")
        .filter { it.isNotEmpty() }
        .map { Stack<Char>() }
    var commands: List<List<Int>> = input.filter { it.startsWith("move") }
        .map { it.split(" ") }
        .map {
            it.filterIndexed { index, _ -> index % 2 == 1 }
                .map { str -> str.toInt() }
        }


    for (crateRow: String in crateRows.reversed()) {
        var stackIdx = 0
        var boxId = crateRow.getOrNull(1) // 4 * stackIdx + 1
        while (boxId != null) {
            if (!boxId.isWhitespace()) {
                crateStacks[stackIdx].push(boxId)
            }
            boxId = crateRow.getOrNull((4 * ++stackIdx) + 1)
        }
    }

    // processing for part 1
//    for (command: List<Int> in commands) {
//        val moveCount: Int = command[0]
//        val moveFrom: Int = command[1] - 1
//        val moveTo: Int = command[2] - 1
//        for(move in 0 until moveCount) {
//            val crateId: Char = crateStacks[moveFrom].pop()
//            crateStacks[moveTo].push(crateId)
//        }
//    }

    // processing for part 2
    for (command: List<Int> in commands) {
        val moveCount: Int = command[0]
        val moveFrom: Int = command[1] - 1
        val moveTo: Int = command[2] - 1

        val crateMover9001Move: MutableList<Char> = mutableListOf()
        for(move in 0 until moveCount) {
            crateMover9001Move.add(crateStacks[moveFrom].pop())
        }

        for(crateId in crateMover9001Move.reversed()) {
            crateStacks[moveTo].push(crateId)
        }
    }

    for (crateStack: Stack<Char> in crateStacks) {
        print(crateStack.pop())
    }
}