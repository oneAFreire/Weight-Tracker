package com.oneafreire.domain.repository

import com.oneafreire.domain.common.BottomBarItem

interface MenuEntriesRepository {
    suspend fun entries(): List<BottomBarItem>
}