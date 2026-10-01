package com.example.rumin.ui.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rumin.R
import com.example.rumin.components.PrimaryButton
import com.example.rumin.components.RemindersCard
import com.example.rumin.components.TimePickerModal
import com.example.rumin.ui.theme.Yellow600
import com.example.rumin.ui.theme.sfRoundedFontFamily

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Reminders() {

    var listOfReminders = remember {
        mutableListOf<Pair<String, String>>()
    }

    var showTimePickerDialog by remember {
        mutableStateOf(false)
    }

//    if(Build.VERSION.SDK_INT >= )

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = if (listOfReminders.size > 1) {
                "You have " + listOfReminders.size + " reminders set"
            } else {
                "You have " + listOfReminders.size + " reminder set"
            },
            fontSize = 24.sp,
            fontFamily = sfRoundedFontFamily,
            fontWeight = FontWeight.Normal,
            color = Yellow600
        )

        listOfReminders.forEach {
            RemindersCard(
                time = it.first,
                periodOfDay = it.second,
                onDelete = {}
            )
        }

        PrimaryButton(
            icon = R.drawable.add,
            label = "Add a reminder",
            onClick = {
                showTimePickerDialog = true
            },
            modifier = Modifier.fillMaxWidth()
        )
    }

    if (showTimePickerDialog) {
        TimePickerModal(
            onConfirm = { timePickerState ->

                val period = if (timePickerState.hour >= 12) "PM" else "AM"

                listOfReminders.add(
                    timePickerState.hour.toString() + ":" + timePickerState.minute.toString() to period
                )
                showTimePickerDialog = false
            },
            onDismiss = {
                showTimePickerDialog = false
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemindersTopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "Reminders",
                fontSize = 24.sp,
                fontFamily = sfRoundedFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Transparent)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun Preview() {
    Reminders()
}