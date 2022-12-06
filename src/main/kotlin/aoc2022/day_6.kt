package aoc2022

fun main() {
    val input = readLines("input/day6.txt")

    val buffer = input[0]
    for (idx in 13 until buffer.length) {
        val first = buffer[idx-13]
        val second = buffer[idx-12]
        val third = buffer[idx-11]
        val fourth = buffer[idx-10]
        val fifth = buffer[idx-9]
        val sixth = buffer[idx-8]
        val seventh = buffer[idx-7]
        val eighth = buffer[idx-6]
        val nineth = buffer[idx-5]
        val tenth = buffer[idx-4]
        val eleventh = buffer[idx-3]
        val twelveth = buffer[idx-2]
        val thirteenth = buffer[idx-1]
        val fourteenth = buffer[idx]

        if(setOf(first, second, third, fourth, fifth, sixth, seventh, eighth, nineth, tenth, eleventh, twelveth, thirteenth, fourteenth).size == 14) {
            println(idx + 1)
            break
        }
    }
}