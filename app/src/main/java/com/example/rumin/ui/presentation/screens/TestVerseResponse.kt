package com.example.rumin.ui.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rumin.data.model.Verse
import com.example.rumin.ui.presentation.viewmodel.RuminViewModel
import com.example.rumin.ui.theme.Grey100
import com.example.rumin.ui.theme.Grey400
import com.example.rumin.ui.theme.Yellow200
import com.example.rumin.ui.theme.Yellow250
import com.example.rumin.ui.theme.Yellow600
import com.example.rumin.utils.RuminUiState
import org.json.JSONObject
import org.jsoup.Jsoup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestVerseResponse(
    ruminViewModel: RuminViewModel
) {
    val verse = ruminViewModel.verse.collectAsState().value

    var searchQuery by remember { mutableStateOf("") }

    var searchBarActive by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = Unit){
        ruminViewModel.getVerse("JHN.1.1")
    }

    when(verse){
        is RuminUiState.Error -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ){
                Text("An Error Occured")
            }
        }
        is RuminUiState.Loading -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {

                CircularProgressIndicator(
                    strokeCap = StrokeCap.Round,
                    modifier = Modifier.size(96.dp),
                    color = Yellow600,
                    trackColor = Yellow200,
                    strokeWidth = 2.dp
                )
            }

        }
        is RuminUiState.Success -> {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp)
            ){

                val rawString = verse.data.verseData.content
                val doc = Jsoup.parse(rawString)

                doc.select("span.v").remove()

                val verseText = doc.select("p.p")

                SearchBar(
                    query = searchQuery,
                    onQueryChange = {
                        searchQuery = it
                    },
                    onSearch = {
                        ruminViewModel.getVerse(searchQuery)
                    },
                    active = searchBarActive,
                    onActiveChange = {
                        searchBarActive = it
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "search",
                            tint = Grey400
                        )
                    },
                    placeholder = {
                        Text(text = "Search...")
                    },
                    shape = RoundedCornerShape(100),
                    colors = SearchBarDefaults.colors(
                        containerColor = Color.White,
                        dividerColor = Grey100
                    )
                ) {
                    Log.d("TEXT", "Output: ${verseText.text()}")
                    Text(
                        text = verseText.text(),
                        fontSize = 24.sp
                    ) //where verse.data is the Success state wrapper's data
                }
            }
        }
    }


}