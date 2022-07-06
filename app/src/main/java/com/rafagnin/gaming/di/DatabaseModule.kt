package com.rafagnin.gaming.di

import android.content.Context
import androidx.room.Room
import com.rafagnin.gaming.data.local.AppDatabase
import com.rafagnin.gaming.data.local.GameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "GamingCatalog"
        ).build()
    }

    @Provides
    fun provideGameDao(appDatabase: AppDatabase): GameDao {
        return appDatabase.gameDao()
    }
}
