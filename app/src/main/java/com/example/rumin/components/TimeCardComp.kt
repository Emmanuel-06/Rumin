//package com.example.rumin.components
//
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Switch
//import androidx.compose.material3.SwitchDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.res.vectorResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.rumin.R
//import com.example.rumin.ui.theme.Black
//import com.example.rumin.ui.theme.Yellow200
//import com.example.rumin.ui.theme.Yellow500
//import com.example.rumin.ui.theme.Yellow600
//import com.example.rumin.ui.theme.sfRoundedFontFamily
//
//@Composable
//fun TimeCardComp(
//    time: String,
//    frequency: String,
//    onClick: () -> Unit,
//) {
//
//    var checked by remember{
//        mutableStateOf(false)
//    }
//
//    Surface(
//        color = Color.White,
//        shape = RoundedCornerShape(20.dp),
//        onClick = onClick,
//        border = BorderStroke(1.dp, Yellow200),
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(14.dp),
//            modifier = Modifier.padding(18.dp)
//            )
//        {
//            Surface(
//                shape = RoundedCornerShape(18.dp),
//                color = Yellow200,
//            ) {
//                Box(
//                    contentAlignment = Alignment.Center,
//                    modifier = Modifier.padding(18.dp)
//                ){
//                    Icon(
//                        imageVector = ImageVector.vectorResource(id = R.drawable.clock_outline),
//                        tint = Yellow500,
//                        contentDescription = null,
//                        modifier = Modifier.size(24.dp)
//                    )
//                }
//            }
//            Column(
//                horizontalAlignment = Alignment.Start,
//                modifier = Modifier.weight(1f)
//            ){
//                Text(
//                    text = time.uppercase(),
//                    fontSize = 24.sp,
//                    fontFamily = sfRoundedFontFamily,
//                    fontWeight = FontWeight.SemiBold,
//                    color = Black
//                )
//
//                Text(
//                    text = frequency,
//                    fontSize = 14.sp,
//                    fontFamily = sfRoundedFontFamily,
//                    fontWeight = FontWeight.Medium,
////                    color = Grey500
//                )
//
//            }
//            Switch(
//                checked = checked,
//                onCheckedChange = {
//                    checked = it
//                },
//                colors = SwitchDefaults.colors(
//                    checkedTrackColor = Yellow500,
//                    uncheckedTrackColor = Yellow600.copy(0.3f),
//                    checkedThumbColor = Color.White,
//                    uncheckedThumbColor = Color.White,
//                    checkedBorderColor = Color.Transparent,
//                    uncheckedBorderColor = Color.Transparent
//                ),
//                modifier = Modifier.clickable(interactionSource = remember{ MutableInteractionSource() }, indication = null){}
//            )
//
//        }
//    }
//
//}
//
//@Preview(showBackground = true)
//@Composable
//fun TimeCardCompPreview() {
//    TimeCardComp(onClick = {}, time = "", frequency = "")
//}