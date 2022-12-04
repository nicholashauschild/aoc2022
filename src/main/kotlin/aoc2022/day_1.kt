package aoc2022

fun main() {
    val input = readLines("input/day1.txt")

    var elfIdx = 0
    var sum = 0
    val elves: MutableList<Elf> = mutableListOf()
    for ( calories : String in input ) {
        if ( calories.isEmpty() ) {
            elves.add(Elf(elfIdx, sum))
            ++elfIdx
            sum = 0
        } else {
            sum += calories.toInt()
        }
    }

    elves.sortByDescending { it.calories }

    println( elves[0].calories )
    println( elves.slice(IntRange(0, 2)).sumOf { it.calories } )
}

data class Elf(val idx: Int, val calories: Int)