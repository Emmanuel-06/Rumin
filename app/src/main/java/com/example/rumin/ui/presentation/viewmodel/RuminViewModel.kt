package com.example.rumin.ui.presentation.viewmodel

import android.net.http.HttpException
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rumin.data.model.Verse
import com.example.rumin.repository.RuminRepository
import com.example.rumin.utils.RuminUiState
import com.example.rumin.utils.SuggestedVerses
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class RuminViewModel @Inject constructor(
    private val ruminRepository: RuminRepository
) : ViewModel() {

    private var _verse = MutableStateFlow<RuminUiState<Verse>>(RuminUiState.Loading)
    val verse = _verse.asStateFlow()

    private var _suggestedVerses = MutableStateFlow<RuminUiState<List<Verse>>>(RuminUiState.Loading)
    val suggestedVerses = _suggestedVerses.asStateFlow()

    private var _selectedVerse = MutableStateFlow<Verse?>(null)
    val selectedVerse = _selectedVerse.asStateFlow()


    fun getVerse(verse: String) {
        _verse.value =
            RuminUiState.Loading //subsequent calls first shows a loading state before the main data is shown
        viewModelScope.launch {
            try {
                _verse.value = RuminUiState.Success(ruminRepository.getVerse(verseId = verse))
            } catch (e: Exception) {
                Log.d("ERR", "Error: ${e.message}")   //error message(s) for debugging
                _verse.value =
                    RuminUiState.Error("Error: ${e.localizedMessage}")   //error message(s) that can be understood by the user
            }
        }
    }


    fun getSuggestedVerses() {
        _suggestedVerses.value = RuminUiState.Loading
        viewModelScope.launch {
            try {
                val randomIds = SuggestedVerses.randomVerseIds.shuffled().take(5)
                val result = randomIds.map { id ->
                    async { ruminRepository.getVerse(id) }
                }.awaitAll()
                _suggestedVerses.value = RuminUiState.Success(result)
            } catch (e: Exception) {
                Log.d("RUMIN ERROR", "${e.message}")
                RuminUiState.Error(e.localizedMessage ?: "An error occurred")
            } catch (e:retrofit2.HttpException){
                Log.d("RUMIN HTTP ERROR", "${e.message}")
            }
        }
    }
    fun selectVerse(verse: Verse){
        _selectedVerse.value = verse
    }
}