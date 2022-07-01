package com.rafagnin.gaming.domain.usecase

import com.rafagnin.gaming.data.repository.GameRepository
import javax.inject.Inject

class GetAllGames @Inject constructor(
    private val repository: GameRepository
) {

    suspend operator fun invoke() = repository.getGames()
}
