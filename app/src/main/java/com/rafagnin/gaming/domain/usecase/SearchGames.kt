package com.rafagnin.gaming.domain.usecase

import com.rafagnin.gaming.data.repository.GameRepository
import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.mapper.GameToDomainMapper
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchGames @Inject constructor(
    private val repository: GameRepository,
    private val mapper: GameToDomainMapper
) {

    operator fun invoke(query: String) = flow {
        try {
            val games = repository.searchGames(query)
                .results
                .map { mapper.map(it) }
            emit(Resource.Success(games))
        } catch (exception: Exception) {
            emit(Resource.Error("error"))
        }
    }
}
