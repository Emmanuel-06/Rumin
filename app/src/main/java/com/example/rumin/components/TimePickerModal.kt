package com.example.rumin.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rumin.ui.theme.Black
import com.example.rumin.ui.theme.Grey200
import com.example.rumin.ui.theme.Grey400
import com.example.rumin.ui.theme.StrokeGrey
import com.example.rumin.ui.theme.Yellow100
import com.example.rumin.ui.theme.Yellow500
import com.example.rumin.ui.theme.sfRoundedFontFamily
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimePickerModal(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit
) {

    val currentTime = LocalTime.now()

    val timePickerState = rememberTimePickerState(
        initialMinute = currentTime.minute,
        initialHour = currentTime.hour,
        is24Hour = false
    )

    TimePickerDialog(
        onDismiss = {
            onDismiss()
        },
        onConfirm = {
            onConfirm(timePickerState)
        }
    ) {
        MaterialTheme(
            colorScheme = MaterialTheme.colorScheme.copy(
                outline = StrokeGrey,
                primary = Yellow500
            ),
            typography = MaterialTheme.typography.copy(
                displayMedium = TextStyle(
                    fontFamily = sfRoundedFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 46.sp
                ) ,
                bodySmall = TextStyle(
                    fontFamily = sfRoundedFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                ),
                labelLarge = TextStyle(
                    fontFamily = sfRoundedFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
            )
        ) {
            TimeInput(
                state = timePickerState,
                colors = TimePickerDefaults.colors(
                    selectorColor = Yellow500,
                    periodSelectorSelectedContainerColor = Yellow500.copy(0.5f),
                    periodSelectorUnselectedContainerColor = Grey200,
                    periodSelectorSelectedContentColor = Black,
                    periodSelectorUnselectedContentColor = Grey400,
                    timeSelectorSelectedContainerColor = Color.White,
                    timeSelectorUnselectedContainerColor = Grey200,
                    timeSelectorSelectedContentColor = Black,
                    timeSelectorUnselectedContentColor = Grey400,
                )
            )
        }
    }
}

@Composable
fun TimePickerDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier
                    .height(40.dp)
            ) {
                Text(
                    text = "Set time",
                    fontFamily = sfRoundedFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        },
        dismissButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(Color.Black.copy(alpha = 0.08f)),
                modifier = Modifier
                    .height(40.dp)
            ) {
                Text(
                    text = "Cancel",
                    fontFamily = sfRoundedFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        },
        title = {
                Text(
                    text = "Set a reminder time",
                    fontFamily = sfRoundedFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start
                )
        },
        text = content,
        shape = RoundedCornerShape(24.dp),
        containerColor = Yellow100
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewTimePickerDialog(){
    TimePickerModal(
        onConfirm = {},
        onDismiss = {}
    )
}