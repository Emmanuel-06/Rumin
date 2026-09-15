package com.example.rumin.ui.presentation.screens

import android.os.Build
import android.widget.Space
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rumin.R
import com.example.rumin.components.CompleteVerseBottomSheet
import com.example.rumin.components.VerseCard
import com.example.rumin.ui.presentation.viewmodel.VerseViewModel
import com.example.rumin.ui.theme.Yellow100
import com.example.rumin.ui.theme.sfRoundedFontFamily
import com.example.rumin.utils.getExtractedVerse
import com.example.rumin.utils.getFormattedDate

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PastVersesList(
    verseViewModel: VerseViewModel,
    navigateToHome: () -> Unit,
) {
    val pastVerses = verseViewModel.pastVerses.collectAsState().value

    var showCompleteVerseBottomSheet by remember {
        mutableStateOf(false)
    }
    var completeVerse by remember {
        mutableStateOf(" " to " ")
    }

    var completeVerseDay by remember {
        mutableStateOf("")
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Verse of the day",
                        fontSize = 16.sp,
                        fontFamily = sfRoundedFontFamily,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = navigateToHome,
                        colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White),
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = ImageVector
                                .vectorResource(id = R.drawable.arrow_left),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
                modifier = Modifier.padding(horizontal = 21.dp)
            )
        },
        containerColor = Yellow100
    ) { innerPadding ->

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 21.dp)
        ) {
            items(pastVerses.drop(1)) { verse ->
                VerseCard(
                    bibleVerse = verse.text,
                    bibleReference = verse.reference.uppercase(),
                    day = getFormattedDate(verse.date),
                    onClick = { bibleVerse, bibleReference, day ->
                        showCompleteVerseBottomSheet = true
                        completeVerse = bibleVerse to bibleReference
                        completeVerseDay = day
                    }
                )

            }
        }
        if(showCompleteVerseBottomSheet){
            CompleteVerseBottomSheet(
                day =completeVerseDay ,
                bibleVerse = completeVerse.first,
                bibleReference = completeVerse.second,
                onShowCompleteVerseBottomSheetChanged = {
                    showCompleteVerseBottomSheet = false
                }
            )
        }
    }
}