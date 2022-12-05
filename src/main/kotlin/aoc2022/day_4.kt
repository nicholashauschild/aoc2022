package aoc2022

fun main() {
    val input = readLines("input/day4.txt")

    var fullyOverlap = 0
    var partlyOverlap = 0
    for (sectionPair in input) {
        val sectionPairs: List<String> = sectionPair.split(",")
        val firstSectionBoundaries: List<Int> = sectionPairs[0].split("-").map{ it.toInt() }
        val sectionSectionBoundaries: List<Int> = sectionPairs[1].split("-").map{ it.toInt() }

        val firstSectionRange = firstSectionBoundaries[0]..firstSectionBoundaries[1]
        val secondSectionRange = sectionSectionBoundaries[0]..sectionSectionBoundaries[1]

        if (firstSectionRange.intersect(secondSectionRange).size in setOf(firstSectionRange.count(), secondSectionRange.count())) {
            fullyOverlap++
        }

        if (firstSectionRange.intersect(secondSectionRange).isNotEmpty()) {
            partlyOverlap++
        }
    }
    println(fullyOverlap)
    println(partlyOverlap)
}