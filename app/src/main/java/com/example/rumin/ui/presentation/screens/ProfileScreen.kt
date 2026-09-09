package com.example.rumin.ui.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rumin.ui.theme.Grey100
import com.example.rumin.ui.theme.Grey200
import com.example.rumin.ui.theme.Grey400
import com.example.rumin.ui.theme.Grey500
import com.example.rumin.ui.theme.Yellow200
import com.example.rumin.ui.theme.Yellow500
import com.example.rumin.ui.theme.Yellow600
import com.example.rumin.ui.theme.sfRoundedFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(18.dp),
                modifier = Modifier
            ) {
                NameAndPhoto()
                StreakAndFavoritesCard()
                Body()
            }
            LogoutButton(
                onButtonClick = {}
            )
        }

    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenTopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "Profile",
                fontSize = 28.sp,
                fontFamily = sfRoundedFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(Yellow200.copy(0.2f))
    )
    
}


@Composable
fun NameAndPhoto() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Surface(
            shape = CircleShape,
            color = Yellow500,
            modifier = Modifier
                .size(56.dp)
                .align(Alignment.CenterVertically)
                .shadow(
                    14.dp,
                    shape = CircleShape,
                    ambientColor = Yellow600,
                    spotColor = Yellow600
                )
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "E",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )

            }
        }
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Emmanuel",
                fontSize = 20.sp,
                fontFamily = sfRoundedFontFamily,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "Reading since Jan 2026",
                fontSize = 14.sp,
                fontFamily = sfRoundedFontFamily,
                fontWeight = FontWeight.Normal,
                color = Grey500
            )
        }
    }
}


@Composable
fun StreakAndFavoritesCard() {
    var data = listOf(
        28 to "Day streak",
        50 to "Verses read",
        0 to "Favourites"
    )
    Card(
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(24.dp),
//        elevation = CardDefaults.cardElevation(10.dp),
        modifier = Modifier
            .shadow(
                30.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = Color.Black.copy(0.3f),
                spotColor = Color.Black.copy(0.3f)
            )
            .height(120.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            data.forEachIndexed { index, it ->
                StreakCardTextComponent(
                    number = it.first,
                    label = it.second
                )
                if (index < data.lastIndex) {
                    VerticalDivider(thickness = 2.dp, color = Grey200)
                }
            }
        }
    }
}

@Composable
fun StreakCardTextComponent(
    number: Int,
    label: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = number.toString(),
            fontSize = 32.sp,
            fontFamily = sfRoundedFontFamily,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = label,
            fontSize = 14.sp,
            fontFamily = sfRoundedFontFamily,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.2.sp,
            color = Grey500
        )
    }
}


@Composable
fun Body() {
    var bodyData = listOf(
        "About Rumin" to null,
        "Notifications Preferences" to null,
    )
    Card(
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.shadow(
            40.dp,
            shape = RoundedCornerShape(20.dp),
            ambientColor = Color.Black.copy(0.3f),
            spotColor = Color.Black.copy(0.3f)
        )
    ) {
        bodyData.forEachIndexed { index, pair ->
            BodyTextComponent(
                label = pair.first,
                content = pair.second,
                onClick = {}
            )
            if (index < bodyData.lastIndex) {
                HorizontalDivider(thickness = 1.dp, color = Grey200)
            }
        }
    }
}


@Composable
fun BodyTextComponent(
    label: String,
    content: String?,
    onClick: () -> Unit,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    onClick()
                }
            )
            .padding(18.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = label,
                fontFamily = sfRoundedFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                letterSpacing = 0.1.sp,
                color = Grey500
            )
            content?.let {
                Text(
                    text = it,
                    fontSize = 14.sp,
//                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = null,
            tint = Grey500
        )

    }
}

@Composable
fun LogoutButton(
    onButtonClick: () -> Unit
) {
    Button(
        onClick = onButtonClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(100),
        border = BorderStroke(1.dp, Grey200),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(
            text = "Logout",
            fontFamily = sfRoundedFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            letterSpacing = 0.1.sp,
            color = Color.Red
        )
        
    }
}
@Preview(showBackground = true)
@Composable
fun ProfilePreviewScreen() {
    Column(
        Modifier.fillMaxSize()
    ) {
        ProfileScreen()
    }
}