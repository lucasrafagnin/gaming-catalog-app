package com.rafagnin.gaming.domain.mapper

import com.rafagnin.gaming.data.model.*
import com.rafagnin.gaming.domain.model.UIGameDetailModel
import javax.inject.Inject

class GameDetailToDomainMapper @Inject constructor() {

    fun map(it: GameModel) = UIGameDetailModel(
        name = it.name,
        image = it.image,
        website = it.website.orEmpty(),
        description = it.description.orEmpty(),
        releaseDate = it.releasedDate,
        score = it.metacritic,
        developersDescription = mapDevelopers(it.developers),
        platformsDescription = mapPlatforms(it.platforms),
        genresDescription = mapGenres(it.genres)
    )

    private fun mapPlatforms(models: List<PlatformResult>?) = models
        ?.map { it.platform }
        ?.map { it.name }
        .run { mapList(this) }

    private fun mapGenres(models: List<GenreModel>?) = models
        ?.map { it.name }
        .run { mapList(this) }

    private fun mapDevelopers(models: List<DeveloperModel>?) = models
        ?.map { it.name }
        .run { mapList(this) }

    private fun mapList(items: List<String>?): String? {
        var result: String? = ""
        items?.forEachIndexed { index, s ->
            result += if (index == 0) s
            else ", $s"
        }
        return result
    }
}
