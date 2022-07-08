package com.rafagnin.gaming.data.repository

import com.rafagnin.gaming.data.local.LocalDataSource
import com.rafagnin.gaming.data.mapper.GameDetailToDomainMapper
import com.rafagnin.gaming.data.mapper.GameToDomainMapper
import com.rafagnin.gaming.data.mapper.UpcomingGameToDomainMapper
import com.rafagnin.gaming.data.remote.RemoteDataSource
import com.rafagnin.gaming.domain.data.GameRepository
import com.rafagnin.gaming.domain.model.UIGameDetailModel
import com.rafagnin.gaming.domain.model.UIGameModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val gameToDomainMapper: GameToDomainMapper,
    private val upcomingGameToDomainMapper: UpcomingGameToDomainMapper,
    private val gameDetailToDomainMapper: GameDetailToDomainMapper
) : GameRepository {

    override fun getFavoriteGames(): Flow<List<UIGameModel>> = localDataSource.getFavoriteGames()
        .map { it.map { model -> gameToDomainMapper.map(model) } }

    override fun isGameFavorite(id: Long) = localDataSource.exist(id)

    override fun favoriteGame(model: UIGameDetailModel, toFavorite: Boolean) {
        if (toFavorite) localDataSource.add(gameToDomainMapper.map(model))
        else localDataSource.remove(gameToDomainMapper.map(model))
    }

    override suspend fun searchGames(query: String) = remoteDataSource.searchGames(query)
        .results
        .map { gameToDomainMapper.map(it) }

    override suspend fun getGames() = remoteDataSource.getGames()
        .results
        .map { gameToDomainMapper.map(it) }

    override suspend fun getGameDetail(id: Long) =
        gameDetailToDomainMapper.map(remoteDataSource.getGameDetail(id))

    override suspend fun getUpcomingGames(limitDate: String) =
        remoteDataSource.getUpcomingGames(limitDate)
            .results
            .map { upcomingGameToDomainMapper.map(it) }
}
