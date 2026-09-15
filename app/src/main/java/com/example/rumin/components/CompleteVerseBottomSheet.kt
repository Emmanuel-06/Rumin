package com.example.rumin.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.rumin.ui.theme.Yellow100
import com.example.rumin.ui.theme.bogueFontFamily
import com.example.rumin.ui.theme.sfRoundedFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteVerseBottomSheet(
    day: String,
    bibleVerse: String,
    bibleReference: String,
    onShowCompleteVerseBottomSheetChanged: () -> Unit
) {

    var sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = {
            onShowCompleteVerseBottomSheetChanged()
        },
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        containerColor = Yellow100,
        scrimColor = Color.Black.copy(0.4f),

        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(horizontal = 21.dp, vertical = 21.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = day,
                    fontSize = 24.sp,
                    fontFamily = sfRoundedFontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(Color.Black),
                modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically, unbounded = true)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(48.dp)
                ) {
                    Text(
                        text = bibleReference.uppercase(),
                        fontSize = 16.sp,
                        fontFamily = sfRoundedFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = bibleVerse,
                        fontSize = 24.sp,
                        fontFamily = bogueFontFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            lineBreak = LineBreak.Heading
                        ),
                        lineHeight = 1.6.em
                    )
                }
            }
        }
    }
}