package com.oneafreire.domain.model

import com.oneafreire.domain.common.DisplayUnits
import com.oneafreire.domain.common.Gender

data class Settings(
    val displayUnits: DisplayUnits = DisplayUnits.KG,
    val gender: Gender? = null,
    val age: Int? = null,
    val height: Int? = null
)