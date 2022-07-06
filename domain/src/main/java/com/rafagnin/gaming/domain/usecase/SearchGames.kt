package com.rafagnin.gaming.domain.usecase

import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.data.GameRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchGames @Inject constructor(
    private val repository: GameRepository,
) {

    operator fun invoke(query: String) = flow {
        try {
            emit(Resource.Success(repository.searchGames(query)))
        } catch (exception: Exception) {
            emit(Resource.Error("error"))
        }
    }
}
