package com.example.rumin.data.api

import com.example.rumin.data.model.Verse
import com.example.rumin.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface VerseApiService {
    @Headers("api-key: ${Constants.API_KEY}")
    @GET("bibles/{bibleId}/verses/{verseId}")
    suspend fun getVerses(
        @Path("bibleId") bibleId: String = Constants.NIV_VERSION,
        @Path("verseId") verseId: String
    ): Verse
}