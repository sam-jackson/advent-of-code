package io.github.samjackson.adventofcode.day03

import java.io.File

fun main(args: Array<String>) {
    val filename = args[0]
    val content = File(filename).readText(Charsets.UTF_8)
    val patternMatcher = Regex("mul\\(([0-9]*),([0-9]*)\\)")
    val result = patternMatcher.findAll(content).map { matchResult ->
        Pair(matchResult.groupValues[1].toDouble(), matchResult.groupValues[2].toDouble())
    }.map { pair ->
        pair.first * pair.second
    }.sum()
    println(String.format("%f", result))
}