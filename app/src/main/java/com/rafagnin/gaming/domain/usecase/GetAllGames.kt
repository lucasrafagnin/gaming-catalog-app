package com.rafagnin.gaming.domain.usecase

import com.rafagnin.gaming.data.repository.GameRepository
import com.rafagnin.gaming.domain.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllGames @Inject constructor(
    private val repository: GameRepository
) {

    operator fun invoke(query: String? = null) = flow {
        try {
            emit(Resource.Success(repository.getGames(query)))
        } catch (exception: Exception) {
            emit(Resource.Error("error"))
        }
    }
}
