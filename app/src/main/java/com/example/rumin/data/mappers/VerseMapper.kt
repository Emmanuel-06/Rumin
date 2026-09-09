package com.example.rumin.data.mappers

import com.example.rumin.data.model.VerseEntity
import com.example.rumin.domain.model.VerseUiModel
import com.example.rumin.utils.NetworkResponse

fun VerseEntity.toVerseModel(): VerseUiModel {
    return VerseUiModel(
        text = this.bibleText,
        reference = this.bibleReference,
        date = this.date
    )
}

fun VerseUiModel.toEntity(): VerseEntity {
    return VerseEntity(
        bibleText = this.text,
        bibleReference = this.reference,
        date = this.date
    )
}