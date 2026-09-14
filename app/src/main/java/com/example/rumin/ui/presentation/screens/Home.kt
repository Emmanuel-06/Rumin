package com.example.rumin.ui.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.rumin.R
import com.example.rumin.components.PrimaryButton
import com.example.rumin.components.VerseCard
import com.example.rumin.ui.presentation.viewmodel.VerseViewModel
import com.example.rumin.ui.theme.Grey400
import com.example.rumin.ui.theme.Yellow100
import com.example.rumin.ui.theme.Yellow300
import com.example.rumin.ui.theme.Yellow500
import com.example.rumin.ui.theme.Yellow600
import com.example.rumin.ui.theme.bogueFontFamily
import com.example.rumin.ui.theme.sfRoundedFontFamily
import com.example.rumin.utils.getExtractedVerse
import org.jsoup.Jsoup
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Home(
    verseViewModel: VerseViewModel,
) {
    val verse = verseViewModel.verseOfTheDay.collectAsState().value

    val pastVerses = verseViewModel.pastVerses.collectAsState().value

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 24.dp)
    ) {
        if (verse.text.isEmpty()) {
            CircularProgressIndicator(color = Yellow600, strokeCap = StrokeCap.Round)
        } else {
            VerseOfTheDayCard(
                bibleReference = verse.reference,
                bibleVerse = getExtractedVerse(verse = verse.text)
            )

            Spacer(modifier = Modifier.height(32.dp))
            ActionButtons()
            Spacer(modifier = Modifier.height(32.dp))
            SectionLabel()
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                pastVerses.drop(2).forEach { verse ->
                    VerseCard(
                        bibleVerse = verse.text,
                        bibleReference = verse.reference,
                        day = verse.date
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeTopAppBar() {

    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM")
    val formattedDate = currentDate.format(formatter)

    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .offset(x = (-16).dp)
                ) {
                    Text(
                        text = formattedDate,
                        fontSize = 14.sp,
                        fontFamily = sfRoundedFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black.copy(0.4f),
                        lineHeight = 12.sp,
                        modifier = Modifier.wrapContentHeight()
                    )

                    Text(
                        text = getGreetingMessage() + ", Emmanuel",
                        fontSize = 16.sp,
                        fontFamily = sfRoundedFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .shadow(
                            24.dp,
                            shape = RoundedCornerShape(100),
                            clip = true,
                            spotColor = Color.Black.copy(0.04f)
                        )
                        .background(Color.White, shape = RoundedCornerShape(100))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        modifier = Modifier.padding(horizontal = 12.dp)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.fire_fill),
                            contentDescription = null,
                            tint = Yellow500,
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = "6",
                            fontSize = 14.sp,
                            fontFamily = sfRoundedFontFamily,
                            fontWeight = FontWeight.Medium
                        )

                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
        modifier = Modifier.padding(horizontal = 21.dp)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun getGreetingMessage(): String {
    return when (LocalDateTime.now().hour) {
        in 0..11 -> "Good morning"
        in 12..16 -> "Good afternoon"
        in 17..21 -> "Good evening"
        else -> "Good night"
    }
}

@Composable
fun VerseOfTheDayCard(
    bibleReference: String,
    bibleVerse: String,
) {
    val customGradient = Brush.verticalGradient(
        0.0f to Yellow100,
        0.6f to Yellow300,
        1f to Yellow500,
    )

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(width = 1.dp, color = Yellow600.copy(0.2f)),
        modifier = Modifier
            .height(440.dp)
            .padding(horizontal = 12.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(brush = customGradient)
                .padding(21.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "VERSE OF THE DAY",
                        fontSize = 14.sp,
                        fontFamily = sfRoundedFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Yellow500,
                        letterSpacing = 0.1.em
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    Text(
                        text = bibleReference.uppercase(),
                        fontSize = 16.sp,
                        fontFamily = sfRoundedFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = bibleVerse,
                        fontSize = 24.sp,
                        fontFamily = bogueFontFamily,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        lineHeight = 1.6.em
                    )
                }
            }
        }

    }
}


@Composable
fun ActionButtons() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        PrimaryButton(
            icon = R.drawable.favourite,
            label = "Favorite"
        )

        PrimaryButton(
            icon = R.drawable.pencil_edit,
            label = "Change Verse"
        )

    }
}

@Composable
fun SectionLabel(
    onClick: () -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Past Days",
            fontSize = 16.sp,
            fontFamily = sfRoundedFontFamily,
            fontWeight = FontWeight.SemiBold
        )

        TextButton(
            onClick = onClick
        ) {
            Text(
                text = "See all",
                fontSize = 12.sp,
                fontFamily = sfRoundedFontFamily,
                fontWeight = FontWeight.Medium,
                color = Grey400
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun HomeTopAppBarPreview() {
    HomeTopAppBar()
}