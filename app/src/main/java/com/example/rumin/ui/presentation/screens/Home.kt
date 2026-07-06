package com.example.rumin.ui.presentation.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
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
import com.example.rumin.data.navbarItems
import com.example.rumin.ui.theme.Black
import com.example.rumin.ui.theme.Grey100
import com.example.rumin.ui.theme.Grey200
import com.example.rumin.ui.theme.Grey400
import com.example.rumin.ui.theme.Grey500
import com.example.rumin.ui.theme.Yellow200
import com.example.rumin.ui.theme.Yellow250
import com.example.rumin.ui.theme.Yellow500
import com.example.rumin.ui.theme.Yellow600
import com.example.rumin.ui.theme.overusedGroteskFontFamily
import kotlinx.coroutines.launch
import java.lang.Math.abs
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {

    var selected by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Text(
                            text = "Monday, June 26th",
//                                 style = MaterialTheme.typography.bodySmall,
                            fontFamily = overusedGroteskFontFamily,
                            color = Grey400,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Text(
                            text = "Good morning, Emmanuel",
//                                 style = MaterialTheme.typography.bodyLarge
                            fontFamily = overusedGroteskFontFamily,
                            fontSize = 18.sp,
                            letterSpacing = -(0.3).sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },
                actions = {
                    Surface(
                        shape = RoundedCornerShape(100),
                        color = Yellow500,
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {

                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.streak),
                                contentDescription = null,
                                tint = Yellow200,
                                modifier = Modifier.size(24.dp)
                            )

                            Text(
                                text = "12 days",
//                                           style = MaterialTheme.typography.bodyMedium,
                                color = Yellow200,
                                fontFamily = overusedGroteskFontFamily,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Yellow200
                ),
                modifier = Modifier
                    .statusBarsPadding()
                    .wrapContentHeight()
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.drawWithContent {
                    drawContent()
                    val strokeWidth = 1.dp.toPx()

                    drawLine(
                        color = Grey200,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        strokeWidth = strokeWidth
                    )
                },
                containerColor = Yellow200,
            ) {
                navbarItems.forEachIndexed { index, navbarItems ->
                    val isSelected = selected == index

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            selected = index
                        },
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = navbarItems.icon),
                                contentDescription = "Nav bar icon",
                                tint = if (isSelected) Black else Grey400,
                                modifier = Modifier
                                    .size(24.dp)
//                                    .clickable(interactionSource, indication = null) {}
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent
                        ),
                        label = {
                            Text(
                                navbarItems.title,
//                                style = MaterialTheme.typography.bodyMedium,
                                fontFamily = overusedGroteskFontFamily,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = if (isSelected) Black else Grey400
                            )
                        },
                        modifier = Modifier
                    )
                }
            }
        },
        containerColor = Yellow200,
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            SwipeableVerseOfTheDay()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeableVerseOfTheDay() {

    var verses by remember {
        mutableStateOf(
            listOf(
                "and raised us up together, and made us sit together in the heavenly places in Christ Jesus" to "Ephesians 2: 6",
                "I will both lie down in peace, and sleep; For You alone, O Lord, make me dwell in safety." to "Psalm 4:8",
                "Trust in the Lord with all your heart, and lean not on your own understanding" to "Proverbs 3: 5",
                "He has delivered us from the power of darkness and conveyed us into the kingdom of the Son of His love" to "Colossians 1:13",
            )
        )
    }

    val animatedOffsetX = remember {
        Animatable(0f)
    }

    val scope = rememberCoroutineScope()
    val visibleCards = verses.take(2)

    var showBottomSheetState by remember {
        mutableStateOf(false)
    }

    var bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    var isSet by remember {
        mutableStateOf(true)
    }

    var selectedItemId by remember {
        mutableStateOf(0)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            visibleCards.reversed().forEachIndexed { reversedCardIndex, verse ->

                key(verse.second) {
                    val visualIndex = 1 - reversedCardIndex

                    val animatedScale by animateFloatAsState(
                        targetValue = 1f - (visualIndex * 0.05f),
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessMedium
                        ),
                        label = "",
                    )

                    val animatedTranslationY by animateFloatAsState(
                        targetValue = visualIndex * 40f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessHigh
                        ),
                        label = ""
                    )


                    VerseOfTheDayCardComponent(
                        bibleVerse = verse.first,
                        bibleText = verse.second,
                        selectCustomVerse = {
                            showBottomSheetState = true
                        },
                        modifier = Modifier
                            .graphicsLayer {
                                scaleX = animatedScale.coerceIn(0.85f, 1f)
                                translationY = animatedTranslationY.coerceAtLeast(10f)
//                            alpha = 1f - (visualIndex * 0.3f)
                            }
                            .then(
                                if (visualIndex == 0) {
                                    Modifier
                                        .graphicsLayer {
                                            rotationZ = animatedOffsetX.value / 200f
                                        }
                                        .offset {
                                            IntOffset(
                                                animatedOffsetX.value.roundToInt(),
                                                0
                                            )
                                        }
                                        .pointerInput(Unit) {
                                            detectDragGestures(
                                                onDrag = { change, dragAmount ->
                                                    change.consume()
                                                    scope.launch {
                                                        animatedOffsetX.snapTo(animatedOffsetX.value + dragAmount.x)
                                                    }
                                                },
                                                onDragEnd = {
                                                    scope.launch {

                                                        if (abs(animatedOffsetX.value) > 300f) {
                                                            verses = verses.drop(1) + verses.take(1)

//                                                        delay(250)
                                                            animatedOffsetX.snapTo(0f)
//                                                        flingjob.cancel()
                                                        } else {
                                                            animatedOffsetX.animateTo(0f)
                                                        }
                                                    }
                                                }
                                            )

                                        }

                                } else {
                                    Modifier
                                }
                            )
                            .shadow(
                                40.dp,
                                RoundedCornerShape(28.dp),
                                ambientColor = Yellow600.copy(0.4f),
                                spotColor = Yellow600.copy(0.4f)
                            )
                    )
                }
            }
        }
        if (showBottomSheetState) {

            SetVerse(
                bottomSheetState = bottomSheetState,
                showBottomSheetState = false,
                onShowBottomSheetStateChanged = {
                    showBottomSheetState = it
                },
                verses = verses,
                isSet = isSet,
                onIsSetChanged = {
                    isSet = !isSet
                }
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
        modifier = modifier
            .height(540.dp)
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
                    color = Yellow600.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(100)
                ) {
                    Text(
                        text = "VERSE OF THE DAY",
                        color = Yellow500,
                        fontFamily = overusedGroteskFontFamily,
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
                    fontFamily = overusedGroteskFontFamily,
                    fontSize = 28.sp,
                    lineHeight = 38.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                )
            }


            Text(
                text = bibleText,
                style = MaterialTheme.typography.bodySmall,
                color = Grey500,
                fontFamily = overusedGroteskFontFamily,
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
                        .border(1.dp, Yellow250, CircleShape)
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
                    fontFamily = overusedGroteskFontFamily,
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
                        .border(1.dp, Yellow250, CircleShape)
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

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home()
}