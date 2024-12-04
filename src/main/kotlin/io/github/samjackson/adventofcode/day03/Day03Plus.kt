package io.github.samjackson.adventofcode.day03

import java.io.File

fun main(args: Array<String>) {
    val filename = args[0]
    val content = "do()" + File(filename).readText(Charsets.UTF_8)
    val filteredContent = content.split("don't()")
        .mapNotNull {
           it.split("do()", limit = 2).getOrNull(1)
        }.joinToString()
    val patternMatcher = Regex("mul\\(([0-9]*),([0-9]*)\\)")
    val result = patternMatcher.findAll(filteredContent).map { matchResult ->
        Pair(matchResult.groupValues[1].toDouble(), matchResult.groupValues[2].toDouble())
    }.map { pair ->
        pair.first * pair.second
    }.sum()
    println(String.format("%f", result))
}
