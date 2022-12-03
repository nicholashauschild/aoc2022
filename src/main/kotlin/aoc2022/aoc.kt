package aoc2022

import java.io.File

fun readLines(file: String): List<String> = File(file).bufferedReader().readLines()

fun readNumbers(file: String): List<Int> = readLines(file).map { it.toInt() }