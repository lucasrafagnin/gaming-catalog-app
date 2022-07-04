package com.rafagnin.gaming.domain.usecase

import com.rafagnin.gaming.data.repository.GameRepository
import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.mapper.GameDetailToDomainMapper
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGameDetail @Inject constructor(
    private val repository: GameRepository,
    private val mapper: GameDetailToDomainMapper
) {

    operator fun invoke(id: Long) = flow {
        try {
            val game = repository.getGameDetail(id)
                .let { mapper.map(it) }
            emit(Resource.Success(game))
        } catch (exception: Exception) {
            emit(Resource.Error("error"))
        }
    }
}
