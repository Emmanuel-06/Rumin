package com.example.rumin.ui.presentation.screens

import android.os.Build
import android.provider.CalendarContract
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rumin.components.TimeCardComp
import com.example.rumin.components.TimePickerModalComp
import com.example.rumin.ui.theme.Black
import com.example.rumin.ui.theme.Grey500
import com.example.rumin.ui.theme.Yellow200
import com.example.rumin.ui.theme.Yellow600
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemindersScreen() {

    var scrollable  = rememberScrollState()

    var times = remember { mutableListOf(
        "10:00am",
        "2:00pm"
    )
    }

    var showTimePickerModal by remember{
        mutableStateOf(false)
    }


    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .statusBarsPadding()
            .background(Yellow200)
            .verticalScroll(
                scrollable
            )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "TIMES",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Grey500,
                letterSpacing = 0.02.sp
            )

            times.forEachIndexed { index, time ->
                TimeCardComp(
                    time = time,
                    frequency = "Everyday"
                ) {
                    showTimePickerModal = true
                }
            }

            TextButton(
                onClick = {
                    showTimePickerModal = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add",
                    tint = Yellow600,
                    modifier = Modifier.size(24.dp)
                )

                Text(
                    text = "Add another time",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Yellow600
                    )
            }

        }
    }

    if(showTimePickerModal){
        TimePickerModalComp(
            onConfirm = { it ->
                val localTime = LocalTime.of(it.hour, it.minute)
                val pattern = if(it.is24hour) "HH:mm" else "hh:mm a"
                var formattedTime = localTime.format(DateTimeFormatter.ofPattern(pattern))

                times.add(formattedTime)

                showTimePickerModal = false
            }
        ) {
            showTimePickerModal = false
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderTopAppBar() {

    TopAppBar(
        title = {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Reminders",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Black
                )
                Text(
                    text = "Choose how often you'd like to be called back to stillness today.",
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    color = Grey500,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Yellow200
        ),
        modifier = Modifier
    )

}


@Preview(showBackground = true)
@Composable
fun RemindersPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ){
//        RemindersScreen()
    }
}