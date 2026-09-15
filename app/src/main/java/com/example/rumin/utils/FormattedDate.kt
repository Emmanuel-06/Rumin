package com.example.rumin.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
fun getFormattedDate(day: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM")

    return day.format(formatter)
}