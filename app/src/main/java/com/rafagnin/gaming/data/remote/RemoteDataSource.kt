package com.rafagnin.gaming.data.remote

import com.rafagnin.gaming.data.remote.service.GamingService
import com.rafagnin.gaming.data.remote.util.SortOption
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val gamingService: GamingService
) {
    suspend fun getGames() = gamingService.getGames()

    suspend fun getUpcomingGames(limitDate: String) = gamingService.getUpcomingGames(
        dates = limitDate,
        ordering = SortOption.RELEASED.type
    )
}
