package com.example.rumin.data.model

import com.google.gson.annotations.SerializedName

data class Verse(
    @SerializedName("data")
    val verseData: Data,
    val meta: Meta
)