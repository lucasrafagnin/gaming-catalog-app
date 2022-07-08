package com.rafagnin.gaming.data.local

import com.rafagnin.gaming.data.model.LocalGameModel
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val gameDao: GameDao
) {
    fun getFavoriteGames() = gameDao.getAll()
    fun exist(id: Long) = gameDao.exist(id)
    fun add(model: LocalGameModel) = gameDao.insert(model)
    fun remove(model: LocalGameModel) = gameDao.delete(model)
}
