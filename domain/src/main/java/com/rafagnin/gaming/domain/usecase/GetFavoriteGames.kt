package com.rafagnin.gaming.domain.usecase

import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.data.GameRepository
import com.rafagnin.gaming.domain.model.UIGameModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteGames @Inject constructor(
    private val repository: GameRepository
) {

    operator fun invoke(): Flow<Resource<List<UIGameModel>>> {
        return repository.getFavoriteGames()
            .map { Resource.Success(it) }
            .catch { Resource.Error<List<GameModel>>(it.message) }
    }
}
