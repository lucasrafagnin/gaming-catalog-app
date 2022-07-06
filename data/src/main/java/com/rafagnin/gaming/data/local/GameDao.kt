package com.rafagnin.gaming.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rafagnin.gaming.data.model.LocalGameModel

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    fun getAll(): List<LocalGameModel>

    @Insert
    fun insert(vararg games: LocalGameModel)

    @Delete
    fun delete(game: LocalGameModel)
}
