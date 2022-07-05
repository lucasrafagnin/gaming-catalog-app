package com.rafagnin.gaming.domain.usecase

import com.rafagnin.gaming.data.repository.GameRepository
import com.rafagnin.gaming.domain.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoriteGames @Inject constructor(
    private val repository: GameRepository
) {

    operator fun invoke() = flow {
        try {
            emit(Resource.Success(listOf<com.rafagnin.gaming.data.model.GameModel>()))
        } catch (exception: Exception) {
            emit(Resource.Error("error"))
        }
    }
}
