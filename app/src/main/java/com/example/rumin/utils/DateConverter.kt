package com.example.rumin.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class DateConverter {
    @TypeConverter
    fun fromLocalDate(date: LocalDate): String {
        return date.toString()
    }

    @TypeConverter
    fun toLocalDate(timestamp: String): LocalDate {
        return LocalDate.parse(timestamp)
    }
}