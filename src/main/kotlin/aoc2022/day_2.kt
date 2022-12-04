package aoc2022

fun main() {
    val input = readLines("input/day2.txt")

    val opponentHands: Map<String, RpsHand> = mapOf(
        Pair("A", RpsHand.Rock),
        Pair("B", RpsHand.Paper),
        Pair("C", RpsHand.Scissors)
    )
    val handScores: Map<RpsHand, Int> = mapOf(
        Pair(RpsHand.Rock, 1),
        Pair(RpsHand.Paper, 2),
        Pair(RpsHand.Scissors, 3)
    )
    val outcomeScores: Map<RpsBattleOutcome, Int> = mapOf(
        Pair(RpsBattleOutcome.WIN, 6),
        Pair(RpsBattleOutcome.DRAW, 3),
        Pair(RpsBattleOutcome.LOSE, 0)
    )
    val recommendedHands: Map<String, RpsHand> = mapOf(
        Pair("X", RpsHand.Rock),
        Pair("Y", RpsHand.Paper),
        Pair("Z", RpsHand.Scissors)
    )
    val desiredOutcomes: Map<String, RpsBattleOutcome> = mapOf(
        Pair("X", RpsBattleOutcome.LOSE),
        Pair("Y", RpsBattleOutcome.DRAW),
        Pair("Z", RpsBattleOutcome.WIN)
    )

    var tournamentScorePart1 = 0
    var tournamentScorePart2 = 0
    for ( round: String in input ) {
        val inn: List<String> = round.split(" ")
        val opponentHand: RpsHand = opponentHands.getValue(inn[0])
        val recommendedHand: RpsHand = recommendedHands.getValue(inn[1])
        val battleOutcome: RpsBattleOutcome = recommendedHand.battle(opponentHand)

        val roundScorePart1: Int = handScores.getValue(recommendedHand) + outcomeScores.getValue(battleOutcome)
        tournamentScorePart1 += roundScorePart1

        val desiredOutcome: RpsBattleOutcome = desiredOutcomes.getValue(inn[1])
        val requiredHand: RpsHand = opponentHand.determineHandFor(desiredOutcome)

        val roundScorePart2: Int = handScores.getValue(requiredHand) + outcomeScores.getValue(desiredOutcome)
        tournamentScorePart2 += roundScorePart2
    }
    println(tournamentScorePart1)
    println(tournamentScorePart2)
}

sealed class RpsHand {
    abstract val beats: RpsHand

    object Rock: RpsHand() {
        override val beats: RpsHand = Scissors
    }
    object Scissors: RpsHand() {
        override val beats: RpsHand = Paper
    }
    object Paper: RpsHand() {
        override val beats: RpsHand = Rock
    }

    fun battle(opponentHand: RpsHand): RpsBattleOutcome = when {
        this.beats == opponentHand ->   RpsBattleOutcome.WIN
        this == opponentHand ->         RpsBattleOutcome.DRAW
        else ->                         RpsBattleOutcome.LOSE
    }

    fun determineHandFor(desiredOutcome: RpsBattleOutcome): RpsHand = when ( desiredOutcome ) {
        RpsBattleOutcome.WIN ->     this.beats.beats
        RpsBattleOutcome.DRAW ->    this
        RpsBattleOutcome.LOSE ->    this.beats
    }
}

enum class RpsBattleOutcome { WIN,LOSE,DRAW }