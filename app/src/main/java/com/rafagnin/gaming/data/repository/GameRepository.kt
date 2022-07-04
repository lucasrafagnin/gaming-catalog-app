package com.rafagnin.gaming.data.repository

import com.rafagnin.gaming.data.remote.RemoteDataSource
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun searchGames(query: String) = remoteDataSource.searchGames(query)

    suspend fun getGames() = remoteDataSource.getGames()

    suspend fun getGameDetail(id: Long) = remoteDataSource.getGameDetail(id)

    suspend fun getUpcomingGames(limitDate: String) = remoteDataSource.getUpcomingGames(limitDate)
}
