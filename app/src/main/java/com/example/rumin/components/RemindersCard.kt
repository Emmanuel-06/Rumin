package com.example.rumin.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.rumin.R
import com.example.rumin.ui.theme.StrokeGrey
import com.example.rumin.ui.theme.Yellow200
import com.example.rumin.ui.theme.Yellow500
import com.example.rumin.ui.theme.Yellow600
import com.example.rumin.ui.theme.sfRoundedFontFamily

@Composable
fun RemindersCard(
    time: String = "9:00",
    periodOfDay: String = "AM",
//    onToggleOn: Boolean,
//    onToggleOnChanged: (Boolean) -> Unit,
    onDelete: () -> Unit
) {

    var arrowMutableState by remember {
        mutableStateOf(false)
    }

    var reminderOn by remember {
        mutableStateOf(false)
    }

    val rotation by animateFloatAsState(
        targetValue = if (arrowMutableState) -180f else 0f,
        animationSpec = tween(500),
        label = "arrow animation"
    )

    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.shadow(
            elevation = 24.dp,
            shape = RoundedCornerShape(16.dp),
            clip = true,
            spotColor = Color.Black.copy(alpha = 0.1f)
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 21.dp, vertical = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = {
                        arrowMutableState = !arrowMutableState
                        expanded = !expanded
                    },
                    colors = IconButtonDefaults.iconButtonColors(Yellow200),
                    modifier = Modifier
                        .size(36.dp)
                        .rotate(rotation)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.caret_arrow),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )

                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontSize = 48.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = sfRoundedFontFamily,
                                color = Color.Black,
                                letterSpacing = (-0.03).em
                            )
                        ) {
                            append(time)
                        }
                        withStyle(
                            SpanStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = sfRoundedFontFamily,
                                color = Color.Black
                            )
                        ) {
                            append(periodOfDay)
                        }
                    }
                )

                Switch(
                    checked = reminderOn,
                    onCheckedChange = {
                        reminderOn = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = Yellow500,
                        checkedThumbColor = Color.White,
                        checkedBorderColor = Color.Transparent,
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color.Black.copy(0.2f),
                        uncheckedBorderColor = Color.Transparent
                    ),
                    modifier = Modifier.height(40.dp)
                )
            }

            AnimatedVisibility(
                visible = expanded,
                modifier = Modifier.wrapContentHeight()
            ) {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = StrokeGrey,
                    modifier = Modifier.fillMaxWidth()
                )

                TextButton(
                    onClick = { onDelete() },
                    colors = ButtonDefaults.buttonColors(contentColor = Color.Red, containerColor = Color.Transparent),
                    contentPadding = PaddingValues(),
                    modifier = Modifier
                        .height(32.dp)
                        .padding(top = 12.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.delete_can),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = "Delete",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = sfRoundedFontFamily,

                            )
                    }

                }

            }

        }

    }
}