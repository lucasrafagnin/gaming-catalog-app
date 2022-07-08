package com.rafagnin.gaming.domain.usecase

import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.data.GameRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IsGameFavorite @Inject constructor(
    private val repository: GameRepository
) {

    operator fun invoke(id: Long) = flow {
        try {
            val games = repository.isGameFavorite(id)
            emit(Resource.Success(games))
        } catch (exception: Exception) {
            emit(Resource.Error("error"))
        }
    }
}
