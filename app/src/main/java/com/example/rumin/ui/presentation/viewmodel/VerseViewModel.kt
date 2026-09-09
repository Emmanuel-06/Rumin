package com.example.rumin.ui.presentation.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rumin.data.api.VerseApiService
import com.example.rumin.domain.model.VerseUiModel
import com.example.rumin.repository.VerseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class VerseViewModel @Inject constructor(
    private val verseRepository: VerseRepository,
    private val verseApiService: VerseApiService
) : ViewModel() {

//    private var _suggestedVerses = MutableStateFlow<NetworkResponse<List<Verse>>>(NetworkResponse.Loading)
//    val suggestedVerses = _suggestedVerses.asStateFlow()
//
//    private var _selectedVerse = MutableStateFlow<Verse?>(null)
//    val selectedVerse = _selectedVerse.asStateFlow()
//
//    private var _isSearching = MutableStateFlow(false)
//    val isSearching = _isSearching.asStateFlow()

    val verseOfTheDay = verseRepository.getVerseOfTheDay()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            initialValue = VerseUiModel("", "", LocalDate.now())
        )

    init {
        viewModelScope.launch {
            Log.d("CHECK_TEST", "Here's you verse: ${verseOfTheDay.value}")
            verseRepository.checkAndFetchData()
        }
        scheduleFetch()
    }

    private fun scheduleFetch() {
        verseRepository.enqueueFetch()
    }

//    fun selectVerse(verse: Verse){
//        _selectedVerse.value = verse
//    }
}



