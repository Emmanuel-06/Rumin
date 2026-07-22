package com.example.rumin.ui.presentation.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rumin.R
import com.example.rumin.data.api.VerseApiService
import com.example.rumin.data.model.Verse
import com.example.rumin.ui.presentation.viewmodel.RuminViewModel
import com.example.rumin.ui.theme.Black
import com.example.rumin.ui.theme.Grey100
import com.example.rumin.ui.theme.Grey200
import com.example.rumin.ui.theme.Grey400
import com.example.rumin.ui.theme.Grey500
import com.example.rumin.ui.theme.Yellow200
import com.example.rumin.ui.theme.Yellow250
import com.example.rumin.ui.theme.Yellow500
import com.example.rumin.ui.theme.Yellow600
import com.example.rumin.utils.RuminUiState
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.lang.Math.abs
import kotlin.math.roundToInt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    ruminViewModel: RuminViewModel
) {

    val suggestedVerse = ruminViewModel.suggestedVerses.collectAsState().value

    val selectedBibleVerse = ruminViewModel.selectedVerse.collectAsState().value

    val verse = ruminViewModel.verse.collectAsState().value



//    var verses by remember {
//        mutableStateOf(
//            listOf(
//                "and raised us up together, and made us sit together in the heavenly places in Christ Jesus" to "Ephesians 2: 6",
//                "I will both lie down in peace, and sleep; For You alone, O Lord, make me dwell in safety." to "Psalm 4:8",
//                "Trust in the Lord with all your heart, and lean not on your own understanding" to "Proverbs 3: 5",
//                "He has delivered us from the power of darkness and conveyed us into the kingdom of the Son of His love" to "Colossians 1:13",
//            )
//        )
//    }
//
//    val animatedOffsetX = remember {
//        Animatable(0f)
//    }

    val scope = rememberCoroutineScope()
//    val visibleCards = verses.take(2)

    var showBottomSheetState by remember {
        mutableStateOf(false)
    }

    var bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    LaunchedEffect(key1 = Unit){
        ruminViewModel.getVerse("JHN.1.1")
    }

    LaunchedEffect(key1 = bottomSheetState){
        ruminViewModel.getSuggestedVerses()
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {

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

                val displayedVerse = selectedBibleVerse ?: verse.data

                val rawString = displayedVerse.verseData.content
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
                        bibleText = displayedVerse.verseData.reference,
                        selectCustomVerse = {
                            showBottomSheetState = true
                        },
                        modifier = Modifier
                    )
                }
        }
    }

//    Column(
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//    ) {
//        Box(
//            contentAlignment = Alignment.Center
//        ) {
//            visibleCards.reversed().forEachIndexed { reversedCardIndex, verse ->
//
//                key(verse.second) {
//                    val visualIndex = 1 - reversedCardIndex
//
//                    val animatedScale by animateFloatAsState(
//                        targetValue = 1f - (visualIndex * 0.05f),
//                        animationSpec = spring(
//                            dampingRatio = Spring.DampingRatioNoBouncy,
//                            stiffness = Spring.StiffnessMedium
//                        ),
//                        label = "",
//                    )
//
//                    val animatedTranslationY by animateFloatAsState(
//                        targetValue = visualIndex * 30f,
//                        animationSpec = spring(
//                            dampingRatio = Spring.DampingRatioMediumBouncy,
//                            stiffness = Spring.StiffnessHigh
//                        ),
//                        label = ""
//                    )
//
//
//                    VerseOfTheDayCardComponent(
//                        bibleVerse = verse.first,
//                        bibleText = verse.second,
//                        selectCustomVerse = {
//                            showBottomSheetState = true
//                            ruminViewModel.getSuggestedVerses()
//                        },
//                        modifier = Modifier
//                            .graphicsLayer {
//                                scaleX = animatedScale.coerceIn(0.85f, 1f)
//                                translationY = animatedTranslationY.coerceAtLeast(10f)
////                            alpha = 1f - (visualIndex * 0.3f)
//                            }
//                            .then(
//                                if (visualIndex == 0) {
//                                    Modifier
//                                        .graphicsLayer {
//                                            rotationZ = animatedOffsetX.value / 200f
//                                        }
//                                        .offset {
//                                            IntOffset(
//                                                animatedOffsetX.value.roundToInt(),
//                                                0
//                                            )
//                                        }
//                                        .pointerInput(Unit) {
//                                            detectDragGestures(
//                                                onDrag = { change, dragAmount ->
//                                                    change.consume()
//                                                    scope.launch {
//                                                        animatedOffsetX.snapTo(animatedOffsetX.value + dragAmount.x)
//                                                    }
//                                                },
//                                                onDragEnd = {
//                                                    scope.launch {
//
//                                                        if (abs(animatedOffsetX.value) > 300f) {
//                                                            verses = verses.drop(1) + verses.take(1)
//
////                                                        delay(250)
//                                                            animatedOffsetX.snapTo(0f)
////                                                        flingjob.cancel()
//                                                        } else {
//                                                            animatedOffsetX.animateTo(0f)
//                                                        }
//                                                    }
//                                                }
//                                            )
//                                        }
//
//                                } else {
//                                    Modifier
//                                }
//                            )
//                            .shadow(
//                                elevation = 30.dp,
//                                ambientColor = Grey500.copy(0.7f),
//                                spotColor = Grey500.copy(0.7f)
//                            )
//                    )
//                }
//            }
//        }
        if (showBottomSheetState) {
            when(suggestedVerse){
                is RuminUiState.Error -> {

                }
                RuminUiState.Loading -> {

                }
                is RuminUiState.Success -> {
                    SetVerse(
                        bottomSheetState = bottomSheetState,
                        showBottomSheetState = false,
                        onShowBottomSheetStateChanged = {
                            showBottomSheetState = it
                        },
                        verses = suggestedVerse.data,
                        ruminViewModel = ruminViewModel
                    )
                }
            }
        }
    }
//}

@Composable
fun VerseOfTheDayCardComponent(
    bibleVerse: String,
    bibleText: String,
    selectCustomVerse: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, Yellow250),
        modifier = modifier
            .height(500.dp)
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Surface(
                    color = Yellow600.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(100)
                ) {
                    Text(
                        text = "VERSE OF THE DAY",
                        color = Yellow600,
                        fontSize = 12.sp,
                        letterSpacing = 1.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                    )
                }
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "\"" + bibleVerse + "\"",
                    style = MaterialTheme.typography.titleLarge,
                    color = Black,
                    fontSize = 24.sp,
                    lineHeight = 34.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                )
            }


            Text(
                text = bibleText,
                style = MaterialTheme.typography.bodySmall,
                color = Grey500,
                fontSize = 18.sp,
                letterSpacing = -(0.1).sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(24.dp))

            HorizontalDivider(thickness = 1.dp, color = Grey200)

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    colors = IconButtonDefaults.iconButtonColors(Grey100),
                    modifier = Modifier
                        .size(48.dp)
                        .indication(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        )
//                        .border(1.dp, Yellow250, CircleShape)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.favorite),
                        contentDescription = null,
                        tint = Grey500,
                        modifier = Modifier
                    )
                }

                Text(
                    text = "swipe to select another verse",
                    style = MaterialTheme.typography.bodySmall,
                    color = Grey400,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )

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
//                        .border(1.dp, Yellow250, CircleShape)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopAppBar() {
    TopAppBar(
        title = {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
            ) {
                Text(
                    text = "Monday 26th, 2026",
                    fontSize = 14.sp,
                    color = Grey500
                )
                Text(
                    text = "Good morning, Emmanuel",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },

        actions = {
            Surface(
                shape = RoundedCornerShape(100),
                color = Yellow500,
                border = BorderStroke(1.dp, Yellow600),
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(12.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.streak),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "12 Days",
                        fontSize = 14.sp,
                        color = Color.White
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