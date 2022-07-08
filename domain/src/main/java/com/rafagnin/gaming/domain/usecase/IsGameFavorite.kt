package com.rafagnin.gaming.domain.usecase

import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.data.GameRepository
import javax.inject.Inject

class IsGameFavorite @Inject constructor(
    private val repository: GameRepository
) {

    operator fun invoke(id: Long): Resource<Boolean> {
        return try {
            Resource.Success(repository.isGameFavorite(id))
        } catch (exception: Exception) {
            Resource.Error("error")
        }
    }
}
