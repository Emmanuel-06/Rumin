package com.example.rumin.ui.presentation.screens

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rumin.R
import com.example.rumin.ui.presentation.viewmodel.VerseViewModel
import com.example.rumin.ui.theme.Black
import com.example.rumin.ui.theme.Grey100
import com.example.rumin.ui.theme.Grey200
import com.example.rumin.ui.theme.Grey400
import com.example.rumin.ui.theme.Grey500
import com.example.rumin.ui.theme.Yellow200
import com.example.rumin.ui.theme.Yellow500
import com.example.rumin.ui.theme.Yellow600
import org.jsoup.Jsoup
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    verseViewModel: VerseViewModel
) {

//    val suggestedVerse = ruminViewModel.suggestedVerses.collectAsState().value

//    val selectedBibleVerse = ruminViewModel.selectedVerse.collectAsState().value

    val verse = verseViewModel.verseOfTheDay.collectAsState().value


    val scope = rememberCoroutineScope()

    var showBottomSheetState by remember {
        mutableStateOf(false)
    }

    var bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )


    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {

    }
        if(verse.text.isEmpty()) {
            CircularProgressIndicator(
                strokeCap = StrokeCap.Round,
                modifier = Modifier.size(96.dp),
                color = Yellow600,
                trackColor = Yellow200,
                strokeWidth = 2.dp
            )
        } else {
//            val displayedVerse = selectedBibleVerse ?: verse.data


            val rawString = verse.text

            val doc = Jsoup.parse(rawString)

            doc.select("span.v, p.s1, p.cl, p.d, p.mr, p.ms1").remove()

            val mainBibleText = doc.select("p")
                .joinToString(separator = " ") { p->
                    p.text()
                }

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
//                        .statusBarsPadding()
//                        .padding(horizontal = 16.dp)
            ) {
                    VerseOfTheDayCardComponent(
                        bibleVerse = mainBibleText,
                        bibleText = verse.reference,
                        selectCustomVerse = {
                            showBottomSheetState = true
                        },
                        modifier = Modifier
                    )
                }
            }
    }

@Composable
fun VerseOfTheDayCardComponent(
    bibleVerse: String,
    bibleText: String,
    selectCustomVerse: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(28.dp),
        border = BorderStroke(1.dp, color = Grey200),
        modifier = modifier
            .height(480.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(
                20.dp,
                shape = RoundedCornerShape(10.dp),
                ambientColor = Grey200,
                spotColor = Grey400
            )
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(32.dp)
                .fillMaxHeight()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Surface(
                    color = Yellow500.copy(0.2f),
                    shape = RoundedCornerShape(100)
                ) {
                    Text(
                        text = "VERSE OF THE DAY",
                        color = Yellow600,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 3.sp,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                    )
                }
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
            ) {
                Text(
                    text = bibleVerse,
                    style = MaterialTheme.typography.titleLarge,
                    color = Black,
                    modifier = Modifier
                )
            }

            Text(
                text = bibleText,
                color = Yellow500,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
            )

            HorizontalDivider(thickness = 1.dp, color = Grey200)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                Text(
                    text = "swipe to see previous days",
                    style = MaterialTheme.typography.labelSmall,
                    color = Grey400,
                    modifier = Modifier.weight(1f)
                )


                IconButton(
                    onClick = { /*TODO*/ },
                    colors = IconButtonDefaults.iconButtonColors(Grey100),
                    modifier = Modifier
                        .size(48.dp)
                        .indication(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        )
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.favorite),
                        contentDescription = null,
                        tint = Grey500,
                        modifier = Modifier
                    )
                }
                IconButton(
                    onClick = {
                        selectCustomVerse()
                    },
                    colors = IconButtonDefaults.iconButtonColors(Grey100),
                    modifier = Modifier
                        .size(48.dp)
                        .indication(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        )
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.add),
                        contentDescription = null,
                        tint = Grey500,
                        modifier = Modifier.size(24.dp)
                    )
                }

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopAppBar() {

    var todayDate by remember{
        mutableStateOf(LocalDate.now())
    }

    val formatter = DateTimeFormatter.ofPattern("E, MMM d", Locale.getDefault())

    val formattedDate = todayDate.format(formatter)

    TopAppBar(
        title = {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
            ) {
                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Grey500
                )
                Text(
                    text = "Good morning, Emmanuel",
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },

        actions = {
            Surface(
                shape = RoundedCornerShape(100),
                color = Color.White,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .shadow(
                        12.dp,
                        shape = RoundedCornerShape(100),
                        ambientColor = Grey200,
                        spotColor = Grey200
                    )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.streak),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "12 days",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Black
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Yellow200
        )
    )
}
@Preview(showBackground = true)
@Composable
fun HomePreview() { }