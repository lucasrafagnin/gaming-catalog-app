package com.rafagnin.gaming.domain.usecase

import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.data.GameRepository
import com.rafagnin.gaming.domain.model.GameModel
import javax.inject.Inject

class SearchGames @Inject constructor(
    private val repository: GameRepository,
) {

    suspend operator fun invoke(query: String): Resource<List<GameModel>> {
        return try {
            Resource.Success(repository.searchGames(query))
        } catch (exception: Exception) {
            Resource.Error(exception.message)
        }
    }
}
