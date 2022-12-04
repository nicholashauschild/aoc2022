package aoc2022

fun main() {
    val input = readLines("input/day3.txt")

    val itemPriorities: Map<Char, Int> = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        .mapIndexed { index, c -> Pair(c, index+1) }
        .toMap()

    var totalMisplacedItemPriority = 0
    var totalBadgeItemPriority = 0
    for ( (index: Int, rucksack: String) in input.withIndex() ) {
        val midIdx = rucksack.length / 2
        val length = rucksack.length

        val compartment1Items: Set<Char> = rucksack.slice(IntRange(0, midIdx-1)).toCharArray().toSet()
        val compartment2Items: Set<Char> = rucksack.slice(IntRange(midIdx, length-1)).toCharArray().toSet()
        val commonItem: Char = compartment1Items.intersect(compartment2Items).first()

        totalMisplacedItemPriority += itemPriorities.getValue(commonItem)

        if ( index % 3 == 2) {
            val elf1Rucksack: Set<Char> = input[index-2].toCharArray().toSet()
            val elf2Rucksack: Set<Char> = input[index-1].toCharArray().toSet()
            val badge: Char = elf1Rucksack
                .intersect(elf2Rucksack)
                .intersect(rucksack.toCharArray().toSet())
                .first()

            totalBadgeItemPriority += itemPriorities.getValue(badge)
        }
    }
    println(totalMisplacedItemPriority)
    println(totalBadgeItemPriority)
}