package com.rafagnin.gaming.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class LocalGameModel(
    @PrimaryKey val id: Long,
    @ColumnInfo val name: String?,
    @ColumnInfo val image: String?,
)
