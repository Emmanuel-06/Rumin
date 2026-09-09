package com.example.rumin.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "verse_of_the_day")
data class VerseEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val bibleText: String,
    val bibleReference: String,
    val date: LocalDate,
)
