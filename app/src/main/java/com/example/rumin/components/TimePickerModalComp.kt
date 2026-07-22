package com.example.rumin.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.rumin.R
import com.example.rumin.ui.theme.Black
import com.example.rumin.ui.theme.Grey200
import com.example.rumin.ui.theme.Grey400
import com.example.rumin.ui.theme.Grey500
import com.example.rumin.ui.theme.Yellow200
import com.example.rumin.ui.theme.Yellow250
import com.example.rumin.ui.theme.Yellow600
import java.time.LocalTime
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerModalComp(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {

    val currentTime = LocalTime.now()

    var timePickerState = rememberTimePickerState(
        initialHour = currentTime.hour,
        initialMinute = currentTime.minute,
        is24Hour = false
    )

    TimePickerDialog(
        onConfirm = { onConfirm(timePickerState) },
        onDismiss = { onDismiss() }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "We'll send you a gentle nudge, at this time everyday",
                color = Grey500,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )

            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme.copy(
                    outline = Grey400,
                    primary = Yellow600,
                    onSurfaceVariant = Grey500
                )
            ) {
                TimeInput(
                    state = timePickerState,
                    colors = TimePickerDefaults.colors(
                        selectorColor = Yellow600,
                        periodSelectorBorderColor = Yellow250,
                        periodSelectorSelectedContainerColor = Color.White,
                        periodSelectorUnselectedContainerColor = Grey200,
                        periodSelectorSelectedContentColor = Black,
                        periodSelectorUnselectedContentColor = Grey400,
                        timeSelectorSelectedContainerColor = Color.White,
                        timeSelectorUnselectedContainerColor = Grey200,
                        timeSelectorSelectedContentColor = Black,
                        timeSelectorUnselectedContentColor = Grey400,
                    ),
                    modifier = Modifier.clickable(
                        interactionSource = null,
                        indication = null
                    ){}
                )
            }
        }

    }
}

@Composable
fun TimePickerDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = { onConfirm() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(100),
                contentPadding = PaddingValues(horizontal = 18.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "Add time",
                    fontWeight = FontWeight.SemiBold,
                )
            }
        },
        dismissButton = {
            Button(
                onClick = { onDismiss() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Grey200,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(100),
                contentPadding = PaddingValues(horizontal = 18.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "Cancel",
                    fontWeight = FontWeight.SemiBold,
                )
            }
        },
        title = {
            Text(
                text = "Add a reminder time",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Black
            )
        },
        icon = {
            Surface(
                shape = CircleShape,
                color = Yellow600.copy(0.1f),
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.reminder),
                    contentDescription = "icon",
                    tint = Yellow600,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(16.dp)
                )
            }
        },
        containerColor = Yellow200,
        text = content,
        shape = RoundedCornerShape(16.dp)
    )
}