package com.rafagnin.gaming.domain.usecase

import com.rafagnin.gaming.data.repository.GameRepository
import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.mapper.GameToDomainMapper
import com.rafagnin.gaming.domain.model.UIGameDetailModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteGame @Inject constructor(
    private val repository: GameRepository,
    private val mapper: GameToDomainMapper
) {

    operator fun invoke(model: UIGameDetailModel) = flow {
        try {
            val games = repository.favoriteGame(mapper.map(model))
            emit(Resource.Success(games))
        } catch (exception: Exception) {
            emit(Resource.Error("error"))
        }
    }
}
