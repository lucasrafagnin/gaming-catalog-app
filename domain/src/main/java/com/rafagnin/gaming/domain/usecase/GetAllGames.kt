package com.rafagnin.gaming.domain.usecase

import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.data.GameRepository
import com.rafagnin.gaming.domain.model.GameModel
import javax.inject.Inject

class GetAllGames @Inject constructor(
    private val repository: GameRepository,
) {

    suspend operator fun invoke(): Resource<List<GameModel>> {
        return try {
            Resource.Success(repository.getGames())
        } catch (exception: Exception) {
            Resource.Error(exception.message)
        }
    }
}
