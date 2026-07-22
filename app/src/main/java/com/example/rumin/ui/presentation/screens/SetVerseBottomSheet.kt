package com.example.rumin.ui.presentation.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rumin.R
import com.example.rumin.components.SearchBarCardContentComp
import com.example.rumin.components.SetVerseCardComp
import com.example.rumin.data.model.Verse
import com.example.rumin.ui.presentation.viewmodel.RuminViewModel
import com.example.rumin.ui.theme.Black
import com.example.rumin.ui.theme.Grey100
import com.example.rumin.ui.theme.Grey200
import com.example.rumin.ui.theme.Grey400
import com.example.rumin.ui.theme.Grey500
import com.example.rumin.ui.theme.Yellow200
import com.example.rumin.ui.theme.Yellow250
import com.example.rumin.ui.theme.Yellow600
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetVerse(
    bottomSheetState: SheetState,
    showBottomSheetState: Boolean,
    onShowBottomSheetStateChanged: (Boolean) -> Unit,
    ruminViewModel: RuminViewModel,
    verses: List<Verse>,
) {

    val scope = rememberCoroutineScope()

    val selectedVerse = ruminViewModel.selectedVerse.collectAsState().value

    var searchQuery by remember {
        mutableStateOf("")
    }

    var active by remember {
        mutableStateOf(false)
    }

    var color = animateColorAsState(
        targetValue = Yellow600,
        label = "color"
    )

    ModalBottomSheet(
        onDismissRequest = {
            onShowBottomSheetStateChanged(showBottomSheetState)
        },
        sheetState = bottomSheetState,
        scrimColor = Color.Black.copy(0.4f),
        windowInsets = WindowInsets(0, 0, 0, 0),
        containerColor = Yellow200,
        shape = RoundedCornerShape(
            topStart = 30.dp,
            topEnd = 30.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        ),
        modifier = Modifier.height(700.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(if (active) 0.dp else 14.dp),
            modifier = Modifier.navigationBarsPadding()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 21.dp)
            ) {
                Text(
                    text = "Set a custom Verse",
                    color = Black,
                    fontSize = 22.sp,
                    letterSpacing = -0.6.sp,
                    fontWeight = FontWeight.Bold
                )

                IconButton(
                    onClick = {
                        scope.launch {
                            bottomSheetState.hide()
                        }.invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                onShowBottomSheetStateChanged(showBottomSheetState)
                            }
                        }
                    },
                    colors = IconButtonDefaults.iconButtonColors(Yellow250),
                    modifier = Modifier
                        .size(46.dp)
                        .indication(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        )
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.close),
                        contentDescription = "hide bottom sheet modal",
                        tint = Grey500
                    )
                }
            }


//            HorizontalDivider(thickness = 1.dp, color = Yellow250)

            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = if (active) 0.dp else 20.dp),
                shape = RoundedCornerShape(100),
                query = searchQuery,
                onQueryChange = {
                    searchQuery = it
                },
                colors = SearchBarDefaults.colors(
                    dividerColor = Grey200,
                    containerColor = if (active) Yellow200 else Color.White
                ),
                leadingIcon = {
                    IconButton(
                        onClick = {
                            if (active) {
                                active = false
                            } else {
                            }
                        }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = if (active) R.drawable.arrow_back else R.drawable.search),
                            contentDescription = null,
                            tint = Black
                        )

                    }
                },
                onSearch = {
                    active = false
                },
                windowInsets = WindowInsets(0, 0, 0, 0),
                placeholder = {
                    Text(
                        text = "search for a verse (e.g: Psalm 23 5, Matthew 28 19", color = Grey400
                    )
                },
                active = active,
                onActiveChange = {
                    active = it
                }
            ) {
                SearchBarCardContentComp()
            }


            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(21.dp, 16.dp)
            ) {
                item {
                    Text(
                        text = "SUGGESTED VERSES",
                        color = Grey400,
                        fontSize = 14.sp,
                        letterSpacing = 2.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                items(
                    items = verses,
                    key = { it.verseData.reference }) { verse ->

                    val isSelected = verse == selectedVerse

                    val rawBibleText = verse.verseData.content

                    val mainBibleText = remember(rawBibleText) {
                        val doc = Jsoup.parse(rawBibleText)
                        doc.select("span.v").remove()
                        doc.select("p.d, p.mr, p.ms1, p.cl").remove()

                        doc.select("p")
                            .joinToString(separator = " ") { p ->
                                p.text()
                            }
                    }

                    SetVerseCardComp(
                        bibleText = verse.verseData.reference,
                        bibleVerse = mainBibleText,
                        isSet = isSelected,
                        onIsSetChanged = {
                            ruminViewModel.selectVerse(verse)
                        }
                    )
                }
            }
        }
    }
}



