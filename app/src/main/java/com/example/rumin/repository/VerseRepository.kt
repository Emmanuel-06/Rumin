package com.example.rumin.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.rumin.data.api.VerseApiService
import com.example.rumin.data.dao.VerseDao
import com.example.rumin.data.mappers.toVerseModel
import com.example.rumin.data.model.VerseEntity
import com.example.rumin.domain.model.VerseUiModel
import com.example.rumin.utils.VerseOfTheDay
import com.example.rumin.workmanager.VerseWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class VerseRepository @Inject constructor(
    private val verseDao: VerseDao,
    private val workManager: WorkManager,
    private val verseApiService: VerseApiService,
) {

    private val currentDate: LocalDate
        get() = LocalDate.now()

    val tomorrowsDate: LocalDate
        get() = LocalDate.now().plusDays(1)

    //call to the Dao class for a single verse and mapping the response from ROOM to our Ui Model class
    fun getVerseOfTheDay(): Flow<VerseUiModel> {
        return verseDao.getVerseOfTheDay(currentDate)
            .map { verse ->
                Log.d("CHECK_OBSERVE", "${verse?.toVerseModel()}")
                verse?.toVerseModel() ?: VerseUiModel("", "", currentDate)
            }
    }

    //call to the Dao class for list of verses for past days and mapping the response from ROOM to our Ui Model class
    fun getAllVerses(): Flow<List<VerseUiModel>>{
        return verseDao.getAllVerses()
            .map { verses ->
                verses.map {
                    it?.toVerseModel() ?: VerseUiModel("", "", currentDate)
                }
            }
    }

    suspend fun insertVerseOfTheDay(verse: VerseEntity) {
        verseDao.insertVerseOfTheDay(verse)
    }

    fun enqueueFetch() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(networkType = NetworkType.CONNECTED)
            .build()

        val verseWorkRequest = PeriodicWorkRequestBuilder<VerseWorker>(
            24, TimeUnit.HOURS
        )
            .addTag("my_verse_tag")
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniquePeriodicWork(
            "fetch_verse",
            ExistingPeriodicWorkPolicy.KEEP,
            verseWorkRequest
        )
    }

    
    suspend fun checkVerseForToday(): Boolean {
        return verseDao.hasVerseForToday(currentDate)
    }

    suspend fun checkVerseForTomorrow(): Boolean {
        Log.d("CHECK_VERSE_FOR_TOMORROW", "${verseDao.hasVerseForTomorrow(tomorrowsDate)}")
        return verseDao.hasVerseForTomorrow(tomorrowsDate)
    }

    suspend fun checkAndFetchData() {
        Log.d("CHECK_VERSE_FOR_TODAY", "${verseDao.hasVerseForToday(LocalDate.now())}")
        if (!checkVerseForToday()) {
            val getAVerse = verseApiService
                .getVerse(
                    verseId = VerseOfTheDay
                        .versesOfTheDay
                        .shuffled()
                        .first()
                )

            val verseResponse = getAVerse.verseData
            Log.d(
                "CHECK_INSERT",
                "${
                    VerseEntity(
                        bibleText = verseResponse.content,
                        bibleReference = verseResponse.reference,
                        date = LocalDate.now()
                    )
                }"
            )
            insertVerseOfTheDay(
                VerseEntity(
                    bibleText = verseResponse.content,
                    bibleReference = verseResponse.reference,
                    date = currentDate
                )
            )
        } else {
        }
    }
}