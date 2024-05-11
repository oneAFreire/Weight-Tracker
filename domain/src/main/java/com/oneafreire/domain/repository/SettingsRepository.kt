package com.oneafreire.domain.repository

import com.oneafreire.domain.model.Settings

interface SettingsRepository {
    suspend fun current(): Settings
}