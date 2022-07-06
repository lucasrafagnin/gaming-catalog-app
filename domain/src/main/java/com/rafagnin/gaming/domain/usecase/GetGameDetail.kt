package com.rafagnin.gaming.domain.usecase

import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.data.GameRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGameDetail @Inject constructor(
    private val repository: GameRepository,
) {

    operator fun invoke(id: Long) = flow {
        try {
            emit(Resource.Success(repository.getGameDetail(id)))
        } catch (exception: Exception) {
            emit(Resource.Error("error"))
        }
    }
}
