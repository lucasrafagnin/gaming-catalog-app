package com.rafagnin.gaming.data.local

import com.rafagnin.gaming.data.model.LocalGameModel
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val gameDao: GameDao
) {
    fun getFavoriteGames() = gameDao.getAll()
    fun favoriteGame(model: LocalGameModel) = gameDao.insert(model)
}
