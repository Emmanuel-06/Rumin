package com.example.rumin.data.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rumin.data.dao.VerseDao
import com.example.rumin.data.model.VerseEntity
import com.example.rumin.utils.DateConverter

@RequiresApi(Build.VERSION_CODES.O)
@Database(
    entities = [VerseEntity::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class VerseDatabase : RoomDatabase() {
    abstract fun verseDao(): VerseDao

}