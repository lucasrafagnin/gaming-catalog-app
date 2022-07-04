package com.rafagnin.gaming.data.repository

import com.rafagnin.gaming.data.remote.RemoteDataSource
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getGames(query: String?) = remoteDataSource.getGames(query)

    suspend fun getGameDetail(id: Long) = remoteDataSource.getGameDetail(id)

    suspend fun getUpcomingGames(limitDate: String) = remoteDataSource.getUpcomingGames(limitDate)
}
