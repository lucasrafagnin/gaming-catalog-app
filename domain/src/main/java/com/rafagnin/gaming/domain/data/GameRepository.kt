package com.rafagnin.gaming.domain.data

import com.rafagnin.gaming.domain.model.UIGameDetailModel
import com.rafagnin.gaming.domain.model.UIGameModel
import com.rafagnin.gaming.domain.model.UIUpcomingGameModel
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getFavoriteGames(): Flow<List<UIGameModel>>
    fun favoriteGame(model: UIGameDetailModel, toFavorite: Boolean)
    fun isGameFavorite(id: Long): Boolean
    suspend fun searchGames(query: String): List<UIGameModel>
    suspend fun getGames(): List<UIGameModel>
    suspend fun getGameDetail(id: Long): UIGameDetailModel
    suspend fun getUpcomingGames(limitDate: String): List<UIUpcomingGameModel>
}
