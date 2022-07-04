package com.rafagnin.gaming.domain.mapper

import com.rafagnin.gaming.data.model.GameModel
import com.rafagnin.gaming.domain.model.UIGameDetailModel
import javax.inject.Inject

class GameDetailToDomainMapper @Inject constructor() {

    fun map(it: GameModel) = UIGameDetailModel(
        name = it.name,
        image = it.image,
        website = it.website.orEmpty(),
        description = it.description.orEmpty(),
        releaseDate = it.releasedDate,
        developers = null,
        platforms = null,
        genres = null
    )

    private fun mapPlatforms() {
    }
}
