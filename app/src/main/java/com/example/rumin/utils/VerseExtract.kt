package com.example.rumin.utils

import org.jsoup.Jsoup

fun getExtractedVerse(verse: String): String{

    val doc = Jsoup.parse(verse)
    doc.select("span.v, p.s1, p.cl, p.d, p.mr, p.ms1").remove()

    val extractedVerseString = doc.select("p")
        .joinToString(separator = " ") { p ->
            p.text()
        }

    return extractedVerseString
}