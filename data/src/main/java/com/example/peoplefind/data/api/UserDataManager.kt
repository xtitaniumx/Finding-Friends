package com.example.peoplefind.data.api

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.peoplefind.domain.model.request.SaveLoginDataParam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_data")

class UserDataManager(private val context: Context) {
    companion object {
        private val USER_ID = stringPreferencesKey("user_id")
        private val AUTO_LOGIN = booleanPreferencesKey("auto_login")
    }

    fun getId(): Flow<String?> {
        return context.dataStore.data.map {
            it[USER_ID]
        }
    }

    fun getLoginState(): Flow<Boolean?> {
        return context.dataStore.data.map {
            it[AUTO_LOGIN]
        }
    }

    suspend fun saveData(param: SaveLoginDataParam) {
        context.dataStore.edit {
            it[USER_ID] = param.userId
            it[AUTO_LOGIN] = param.rememberState
        }
    }

    suspend fun deleteAllData() {
        context.dataStore.edit {
            it.remove(USER_ID)
            it.remove(AUTO_LOGIN)
        }
    }
}