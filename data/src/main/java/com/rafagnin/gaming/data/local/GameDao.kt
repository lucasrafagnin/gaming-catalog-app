package com.rafagnin.gaming.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rafagnin.gaming.data.model.LocalGameModel
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    fun getAll(): Flow<List<LocalGameModel>>

    @Insert
    fun insert(vararg games: LocalGameModel)

    @Delete
    fun delete(game: LocalGameModel)

    @Query("SELECT EXISTS(SELECT * FROM game WHERE id = :id)")
    fun exist(id: Long): Boolean
}
