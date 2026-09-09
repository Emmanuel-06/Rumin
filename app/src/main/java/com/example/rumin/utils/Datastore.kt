package com.example.rumin.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import java.util.prefs.Preferences

class Datastore(
    val context: Context,
) {
    private val Context.dataStore by preferencesDataStore(name = "already shown verses")

    suspend fun save(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        context.dataStore.edit { verse ->
            verse[dataStoreKey] = value
        }
    }

    suspend fun read(key: String): String? {
        val datastoreKey = stringPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[datastoreKey]
    }
}

