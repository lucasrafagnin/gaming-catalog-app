package com.rafagnin.gaming.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rafagnin.gaming.data.model.LocalGameModel

@Database(entities = [LocalGameModel::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}
