package com.example.rumin.utils

import com.example.rumin.data.model.Verse

sealed class RuminUiState<out T> {
    data class Success<out T>(val data: T): RuminUiState<T>()
    data class Error(val message: String): RuminUiState<Nothing>()
    data object Loading: RuminUiState<Nothing>()
}
