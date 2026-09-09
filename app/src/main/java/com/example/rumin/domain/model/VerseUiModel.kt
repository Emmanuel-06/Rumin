package com.example.rumin.domain.model

import androidx.room.PrimaryKey
import java.time.LocalDate

data class VerseUiModel(
    val text: String,
    val reference: String,
    val date: LocalDate
)