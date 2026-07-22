package com.example.rumin.data.model

data class Data(
    val bibleId: String,
    val bookId: String,
    val chapterId: String,
    val content: String,
    val copyright: String,
    val id: String,
    val next: Next,
    val orgId: String,
    val previous: Previous,
    val reference: String,
    val verseCount: Int
)