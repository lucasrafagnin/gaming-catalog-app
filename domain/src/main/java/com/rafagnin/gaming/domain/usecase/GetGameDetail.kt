package com.rafagnin.gaming.domain.usecase

import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.data.GameRepository
import com.rafagnin.gaming.domain.model.GameDetailModel
import javax.inject.Inject

class GetGameDetail @Inject constructor(
    private val repository: GameRepository,
    private val isGameFavorite: IsGameFavorite
) {

    suspend operator fun invoke(id: Long): Resource<GameDetailModel> {
        return try {
            Resource.Success(
                repository.getGameDetail(id).copy(
                    favorite = isGameFavorite.invoke(id).data == true
                )
            )
        } catch (exception: Exception) {
            Resource.Error(exception.message)
        }
    }
}
