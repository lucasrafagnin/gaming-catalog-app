package com.rafagnin.gaming.data.local

import com.rafagnin.gaming.data.model.LocalGameModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val gameDao: GameDao
) {
    fun getFavoriteGames(): Flow<List<LocalGameModel>> = gameDao.getAll()
    fun exist(id: Long) = gameDao.exist(id)
    fun add(model: LocalGameModel) = gameDao.insert(model)
    fun remove(model: LocalGameModel) = gameDao.delete(model)
}
