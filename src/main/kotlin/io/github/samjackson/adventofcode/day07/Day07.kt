package io.github.samjackson.adventofcode.day07

import java.io.File

fun main(args: Array<String>) {
    val filename = args[0]
    val input = File(filename).readLines(Charsets.UTF_8)
    val sum = input.map { line ->
        line.split(Regex(":?\\s")).map { s -> s.toLong() }
    }.map { lineArray ->
        val equation = Equation(lineArray[0])
        val stack = lineArray.subList(2, lineArray.size)
        if (equation.prove(stack, lineArray[1]))
            equation.reportedTestValue
        else
            0L
    }.sum()
    println(sum)
}

val operators : List<(Long, Long) -> Long> = listOf(
    fun (a, b) = a + b,
    fun (a, b) = a * b,
    fun (a, b) = (a.toString() + b.toString()).toLong() // part two
)

class Equation(
    val reportedTestValue: Long
) {
    fun prove(remaining: List<Long>, runningTotal: Long = 0): Boolean {
        if (remaining.isEmpty()) {
            return reportedTestValue == runningTotal
        }
        if (runningTotal > reportedTestValue ) {
            return false
        }

        val nextTest = remaining[0]
        val nextList = remaining.subList(1, remaining.size)
        return operators.any { op ->
            prove(nextList, op.invoke(runningTotal, nextTest))
        }
    }
}