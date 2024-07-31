package com.oneafreire.domain.repository

import com.oneafreire.domain.model.Settings

interface SettingsRepository {
    fun current(): Settings
    fun save(settings: Settings)
}