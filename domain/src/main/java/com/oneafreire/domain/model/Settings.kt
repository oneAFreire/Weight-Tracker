package com.oneafreire.domain.model

import com.oneafreire.domain.common.DisplayUnits
import com.oneafreire.domain.common.Gender

data class Settings(
    val displayUnits: DisplayUnits,
    val gender: Gender,
    val age: Int,
    val height: Int
)