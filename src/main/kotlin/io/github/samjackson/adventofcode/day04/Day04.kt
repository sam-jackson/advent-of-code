package io.github.samjackson.adventofcode.day04

import java.io.File

val incSequences = arrayOf(
    Pair(0, 1),
    Pair(1, 0),
    Pair(0, -1),
    Pair(-1, 0),
    Pair(1, 1),
    Pair(-1, -1),
    Pair(1, -1),
    Pair(-1, 1)
)

fun main(args: Array<String>) {
    val filename = args[0]
    val thegrid = File(filename).readLines(Charsets.UTF_8).map {
        line -> line.toCharArray()
    }

    var found = 0;
    for (i in thegrid.indices) {
        for (j in thegrid[i].indices) {
            found += incSequences.map {
                isXmas(gridSeq( thegrid, i, j, it.first, it.second))
            }.filter { it }.count()
        }
    }

    println(found)
}

fun gridSeq(thegrid: List<CharArray>, xpos: Int, ypos: Int, xposInc: Int, yposInc: Int) = sequence<Char> {
    var x = xpos
    var y = ypos
    do {
        yield(thegrid[x][y])
        x += xposInc
        y += yposInc
    } while(thegrid.getOrNull(x) != null && thegrid[x].getOrNull(y) != null)
}

val xmas = "XMAS".toCharArray().toList()
fun isXmas(sequence: Sequence<Char>) : Boolean {
    var index = 0
    return sequence.take(4).toList().equals(xmas)
}