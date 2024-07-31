package com.oneafreire.data.repositoryimpl

import android.content.SharedPreferences
import com.google.gson.Gson
import com.oneafreire.domain.model.Settings
import com.oneafreire.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : SettingsRepository {
    companion object {
        const val KEY_SETTINGS = "key_settings"
    }

    override fun save(settings: Settings) {
        val json = gson.toJson(settings)
        sharedPreferences.edit().putString(KEY_SETTINGS, json).apply()
    }

    override fun current(): Settings {
        val json = sharedPreferences.getString(KEY_SETTINGS, null)
        return if (json != null) {
            gson.fromJson(json, Settings::class.java)
        } else {
            Settings()
        }
    }
}