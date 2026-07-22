package com.example.rumin.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rumin.data.model.Verse
import com.example.rumin.ui.theme.Black
import com.example.rumin.ui.theme.Grey100
import com.example.rumin.ui.theme.Grey200
import com.example.rumin.ui.theme.Grey500
import com.example.rumin.ui.theme.Yellow250
import com.example.rumin.ui.theme.Yellow600
import com.example.rumin.ui.theme.overusedGroteskFontFamily

@Composable
fun SetVerseCardComp(
    bibleText: String,
    bibleVerse: String,
    isSet: Boolean,
    onIsSetChanged: (String) -> Unit,
) {

    val borderColor by animateColorAsState(
        targetValue = if (isSet) Yellow600 else Color.Transparent,
        label = "color"
    )


    Card(
        shape = RoundedCornerShape(10),
        colors = if(isSet) CardDefaults.cardColors(Yellow600.copy(0.05f)) else CardDefaults.cardColors(
            Color.White),
        border = if(isSet) BorderStroke(1.dp, borderColor) else BorderStroke(0.dp, borderColor),
        onClick = {}
    ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = bibleText,
                    color = Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = "\"" + bibleVerse + "\"" ,
                    color = Grey500,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Normal,
                )

                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.End)
                ){
                    Button(
                        onClick = {
                            onIsSetChanged(bibleText)
                        },
                        shape = RoundedCornerShape(100),
                        colors = if(isSet) ButtonDefaults.buttonColors(Yellow600) else ButtonDefaults.buttonColors(
                            Yellow250)
                    ) {
                        Text(
                            text = if(isSet) "Verse Set" else "Set for today",
                            color = if(isSet) Color.White else Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    }
            }
        }
    }
}