package com.oneafreire.data.repositoryimpl

import com.oneafreire.domain.common.BottomBarItem
import com.oneafreire.domain.repository.MenuEntriesRepository

class MenuEntriesRepositoryImpl : MenuEntriesRepository {
    override suspend fun entries(): List<BottomBarItem> {
        return listOf(BottomBarItem.HOME, BottomBarItem.STATISTICS, BottomBarItem.RECORDS)
    }
}