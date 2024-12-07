package io.github.samjackson.adventofcode.day05

import java.io.File
import java.util.*
import kotlin.collections.ArrayDeque

fun main (args: Array<String>) {
    val filename = args[0]
    val lines = ArrayDeque<String>(File(filename).readLines(Charsets.UTF_8))
    val rules : MutableMap<Int, List<Rule>> = TreeMap<Int, List<Rule>>()

    while (lines.firstOrNull()?.isNotBlank() == true) {
        val line = lines.removeFirst()
        val currentRule = line.split("|")
        val rule = Rule(
            currentRule[1].toInt(),
            currentRule[0].toInt()
        )
        var pagesYouMustHaveSeen = rules.getOrDefault(rule.toHaveThisPage, setOf())
        pagesYouMustHaveSeen = pagesYouMustHaveSeen.plus(rule)
        rules[rule.toHaveThisPage] = pagesYouMustHaveSeen
    }

    lines.removeFirst()
    var middlePages = mutableListOf<Int>()

    while (lines.firstOrNull()?.isNotBlank() == true) {
        val line = lines.removeFirst()
        val pages = line.split(",").map { it.toInt() }

        var seenPages = mutableListOf<Int>()
        var valid = true

        for (page in pages) {
            seenPages.add(page)
            // the valid rules only those if the pageset would include that page.
            val validRules = rules[page]?.filter { rule -> pages.contains(rule.youMustHaveSeen) }?.map {
                it.youMustHaveSeen
            } ?: continue;

            if (!seenPages.containsAll(validRules)) {
                valid = false
            }
        }

        if (valid) {
            middlePages.add(pages[pages.size / 2])
        }
    }

    println(middlePages.sum())
}

class Rule(
    val toHaveThisPage : Int,
    val youMustHaveSeen : Int
)
