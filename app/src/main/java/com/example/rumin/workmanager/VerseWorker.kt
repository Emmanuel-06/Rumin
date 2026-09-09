package com.example.rumin.workmanager

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.rumin.data.api.VerseApiService
import com.example.rumin.data.dao.VerseDao
import com.example.rumin.data.model.VerseEntity
import com.example.rumin.repository.VerseRepository
import com.example.rumin.utils.VerseOfTheDay
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.LocalDate

@HiltWorker
class VerseWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val apiService: VerseApiService,
    private val verseRepository: VerseRepository
) : CoroutineWorker(context, workerParams) {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {

        return try {
            if (!verseRepository.checkVerseForTomorrow()) {
                // fetch verse from API
                val response = apiService.getVerse(
                    verseId = VerseOfTheDay.versesOfTheDay
                        .shuffled().first()
                )
                //store verse in database
                verseRepository.insertVerseOfTheDay(
                    VerseEntity(
                        bibleText = response.verseData.content,
                        bibleReference = response.verseData.reference,
                        date = verseRepository.tomorrowsDate
                    )
                )
            } else { }
            Result.success()
        } catch (e: retrofit2.HttpException) {
            Log.d("VERSE_WORKER", "${e.message}")
            Result.retry()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }

}