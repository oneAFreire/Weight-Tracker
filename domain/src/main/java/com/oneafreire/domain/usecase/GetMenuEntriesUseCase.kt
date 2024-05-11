package com.oneafreire.domain.usecase

import com.oneafreire.domain.common.BottomBarItem
import com.oneafreire.domain.repository.MenuEntriesRepository

class GetMenuEntriesUseCase(
    private val repository: MenuEntriesRepository
) {
    suspend operator fun invoke(): List<BottomBarItem> {
        return repository.entries()
    }
}