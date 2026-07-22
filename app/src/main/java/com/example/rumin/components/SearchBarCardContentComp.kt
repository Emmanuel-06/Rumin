package com.example.rumin.components

import android.view.RoundedCorner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rumin.ui.theme.Black
import com.example.rumin.ui.theme.Grey100

@Composable
fun SearchBarCardContentComp(
//    bookSelected: () -> Unit,
//    chapterSelected: () -> Unit
) {

    Surface(
        color = Color.White,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 18.dp)
        ) {
            Text(
                text = "Genesis",
                color = Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(20){ it ->
                    ChapterCard(
                        number = "$it",
                        onClick = {}
                    )
                }
            }

        }
    }
}

@Composable
fun ChapterCard(
    number: String,
    onClick: () -> Unit,
) {
    Surface(
        color = Grey100,
        shape = RoundedCornerShape(12.dp),
        onClick = onClick,
    ) {
        Text(
            text = number,
            color = Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    SearchBarCardContentComp()
}