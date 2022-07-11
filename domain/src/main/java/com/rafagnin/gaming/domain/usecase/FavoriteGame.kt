package com.rafagnin.gaming.domain.usecase

import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.data.GameRepository
import com.rafagnin.gaming.domain.model.GameDetailModel
import javax.inject.Inject

class FavoriteGame @Inject constructor(
    private val repository: GameRepository
) {

    operator fun invoke(model: GameDetailModel, toFavorite: Boolean): Resource<GameDetailModel> {
        return try {
            repository.favoriteGame(model, toFavorite)
            Resource.Success(model.copy(favorite = toFavorite))
        } catch (exception: Exception) {
            Resource.Error(exception.message)
        }
    }
}
