package com.rafagnin.gaming.domain.usecase

import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.data.GameRepository
import com.rafagnin.gaming.domain.model.UIGameDetailModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteGame @Inject constructor(
    private val repository: GameRepository
) {

    operator fun invoke(model: UIGameDetailModel) = flow {
        try {
            val games = repository.favoriteGame(model)
            emit(Resource.Success(games))
        } catch (exception: Exception) {
            emit(Resource.Error("error"))
        }
    }
}
