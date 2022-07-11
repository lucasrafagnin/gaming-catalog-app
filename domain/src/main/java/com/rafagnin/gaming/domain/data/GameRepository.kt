package com.rafagnin.gaming.domain.data

import com.rafagnin.gaming.domain.model.GameDetailModel
import com.rafagnin.gaming.domain.model.GameModel
import com.rafagnin.gaming.domain.model.UpcomingGameModel
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getFavoriteGames(): Flow<List<GameModel>>
    fun favoriteGame(model: GameDetailModel, toFavorite: Boolean)
    fun isGameFavorite(id: Long): Boolean
    suspend fun searchGames(query: String): List<GameModel>
    suspend fun getGames(): List<GameModel>
    suspend fun getGameDetail(id: Long): GameDetailModel
    suspend fun getUpcomingGames(limitDate: String): List<UpcomingGameModel>
}
