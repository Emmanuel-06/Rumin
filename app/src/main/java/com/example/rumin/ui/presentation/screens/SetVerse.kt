package com.example.rumin.ui.presentation.screens

import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rumin.R
import com.example.rumin.components.SetVerseCardComp
import com.example.rumin.ui.theme.Black
import com.example.rumin.ui.theme.Grey100
import com.example.rumin.ui.theme.Grey200
import com.example.rumin.ui.theme.Grey400
import com.example.rumin.ui.theme.Grey500
import com.example.rumin.ui.theme.Yellow200
import com.example.rumin.ui.theme.Yellow250
import com.example.rumin.ui.theme.Yellow300
import com.example.rumin.ui.theme.overusedGroteskFontFamily
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetVerse(
    bottomSheetState: SheetState,
    showBottomSheetState: Boolean,
    onShowBottomSheetStateChanged: (Boolean) -> Unit,
    verses: List<Pair<String, String>>,
    isSet: Boolean,
    onIsSetChanged: () -> Unit,
) {

    val scope = rememberCoroutineScope()

    var selectedItemId by remember {
        mutableStateOf(verses[0].second)
    }

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
            verticalArrangement = Arrangement.spacedBy(14.dp),
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
                    fontFamily = overusedGroteskFontFamily,
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
                        .size(38.dp)
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
            HorizontalDivider(thickness = 0.8.dp, color = Yellow250)

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(21.dp, 16.dp)
            ) {
                item {
                    Text(
                        text = "SUGGESTED VERSES",
                        color = Grey400,
                        fontFamily = overusedGroteskFontFamily,
                        fontSize = 14.sp,
                        letterSpacing = 2.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                items(
                    items = verses,
                    key = { it.second } ) { verse ->

                    val isSelected = verse.second == selectedItemId

                    SetVerseCardComp(
                        bibleText = verse.second,
                        bibleVerse = verse.first,
                        isSet = isSelected,
                        onIsSetChanged = {
                            selectedItemId = it
                        }
                    )
                }
            }
        }

    }

}



