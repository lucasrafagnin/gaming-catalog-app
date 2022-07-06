package com.rafagnin.gaming.di

import com.rafagnin.gaming.data.local.LocalDataSource
import com.rafagnin.gaming.data.mapper.GameDetailToDomainMapper
import com.rafagnin.gaming.data.mapper.GameToDomainMapper
import com.rafagnin.gaming.data.mapper.UpcomingGameToDomainMapper
import com.rafagnin.gaming.data.remote.RemoteDataSource
import com.rafagnin.gaming.data.repository.GameRepositoryImpl
import com.rafagnin.gaming.domain.data.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideGameRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        gameToDomainMapper: GameToDomainMapper,
        upcomingGameToDomainMapper: UpcomingGameToDomainMapper,
        gameDetailToDomainMapper: GameDetailToDomainMapper
    ): GameRepository {
        return GameRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            gameToDomainMapper = gameToDomainMapper,
            upcomingGameToDomainMapper = upcomingGameToDomainMapper,
            gameDetailToDomainMapper = gameDetailToDomainMapper
        )
    }
}
