package com.rafagnin.gaming.domain.usecase

import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.data.GameRepository
import com.rafagnin.gaming.domain.model.UIGameDetailModel
import javax.inject.Inject

class FavoriteGame @Inject constructor(
    private val repository: GameRepository
) {

    operator fun invoke(model: UIGameDetailModel, toFavorite: Boolean): Resource<UIGameDetailModel> {
        return try {
            repository.favoriteGame(model, toFavorite)
            Resource.Success(model.copy(favorite = toFavorite))
        } catch (exception: Exception) {
            Resource.Error("error")
        }
    }
}
