package com.rafagnin.gaming.domain.usecase

import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.data.GameRepository
import com.rafagnin.gaming.domain.model.UIGameDetailModel
import javax.inject.Inject

class GetGameDetail @Inject constructor(
    private val repository: GameRepository,
) {

    suspend operator fun invoke(id: Long): Resource<UIGameDetailModel> {
        return try {
            Resource.Success(repository.getGameDetail(id))
        } catch (exception: Exception) {
            Resource.Error("error")
        }
    }
}
