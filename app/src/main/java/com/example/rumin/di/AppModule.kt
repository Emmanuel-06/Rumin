package com.example.rumin.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import androidx.work.WorkManager
import com.example.rumin.data.api.VerseApiService
import com.example.rumin.data.dao.VerseDao
import com.example.rumin.data.database.VerseDatabase
import com.example.rumin.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesVerseApiService(retrofit: Retrofit): VerseApiService {
        return retrofit.create(VerseApiService::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): VerseDatabase {
        return Room.databaseBuilder(
            context,
            VerseDatabase::class.java,
            "verseOfTheDay_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesVerseDao(database: VerseDatabase): VerseDao{
        return database.verseDao()
    }

    @Singleton
    @Provides
    fun providesWorkManager(@ApplicationContext context: Context): WorkManager{
        return WorkManager.getInstance(context)
    }
}