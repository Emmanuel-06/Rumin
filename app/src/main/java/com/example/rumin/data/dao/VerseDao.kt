package com.example.rumin.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rumin.data.model.VerseEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface VerseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVerseOfTheDay(verse: VerseEntity)
    @Query("SELECT * FROM verse_of_the_day WHERE date =:date LIMIT 1")
    fun getVerseOfTheDay(date: LocalDate): Flow<VerseEntity?>

    @Query("SELECT EXISTS (SELECT 1 FROM verse_of_the_day WHERE date = :today)")
    suspend fun hasVerseForToday(today: LocalDate): Boolean

    @Query("SELECT EXISTS (SELECT 1 FROM verse_of_the_day WHERE date = :tomorrow)")
    suspend fun hasVerseForTomorrow(tomorrow: LocalDate): Boolean

}