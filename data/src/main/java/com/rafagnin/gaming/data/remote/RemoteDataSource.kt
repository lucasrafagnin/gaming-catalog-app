package com.rafagnin.gaming.data.remote

import com.rafagnin.gaming.data.remote.service.GamingService
import com.rafagnin.gaming.data.remote.util.SortOption
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val gamingService: GamingService
) {
    suspend fun searchGames(query: String) = gamingService.searchGames(query)

    suspend fun getGames() = gamingService.getGames()

    suspend fun getGameDetail(id: Long) = gamingService.getGameDetail(id)

    suspend fun getUpcomingGames(limitDate: String) = gamingService.getUpcomingGames(
        dates = limitDate,
        ordering = SortOption.RELEASED.type
    )
}
