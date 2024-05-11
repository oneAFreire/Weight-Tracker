package com.oneafreire.data.repositoryimpl

import com.oneafreire.domain.common.DisplayUnits
import com.oneafreire.domain.common.Gender
import com.oneafreire.domain.model.Settings
import com.oneafreire.domain.repository.SettingsRepository

class SettingsRepositoryImpl(): SettingsRepository {
    override suspend fun current(): Settings {
        // TODO
        return Settings(
            displayUnits = DisplayUnits.KG,
            gender = Gender.MALE,
            age = 37,
            height = 178
        )
    }
}