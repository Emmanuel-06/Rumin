package com.example.rumin.repository

import android.util.Log
import com.example.rumin.data.api.VerseApiService
import com.example.rumin.data.model.Verse
import com.example.rumin.utils.RuminUiState
import javax.inject.Inject

class RuminRepository @Inject constructor(
    private val api: VerseApiService
) {
    suspend fun getVerse(verseId: String) : Verse {
        return api.getVerses(verseId = verseId)
    }
}