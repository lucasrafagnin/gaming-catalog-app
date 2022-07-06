package com.rafagnin.gaming.data.local

import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val gameDao: GameDao
) {
    fun getFavoriteGames() = gameDao.getAll()
}
