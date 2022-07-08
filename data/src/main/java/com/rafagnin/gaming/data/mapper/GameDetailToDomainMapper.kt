package com.rafagnin.gaming.data.mapper

import com.rafagnin.gaming.data.model.DeveloperModel
import com.rafagnin.gaming.data.model.GameModel
import com.rafagnin.gaming.data.model.GenreModel
import com.rafagnin.gaming.data.model.PlatformResult
import com.rafagnin.gaming.data.model.TagModel
import com.rafagnin.gaming.domain.model.UIGameDetailModel
import com.rafagnin.gaming.domain.util.ScoreLevel
import javax.inject.Inject

class GameDetailToDomainMapper @Inject constructor() {

    fun map(it: GameModel) = UIGameDetailModel(
        id = it.id,
        name = it.name,
        image = it.image,
        website = it.website,
        description = it.description,
        releaseDate = it.releasedDate,
        score = it.metacritic,
        scoreBg = getScore(it.metacritic),
        tags = mapTags(it.tags),
        developersDescription = mapDevelopers(it.developers),
        platformsDescription = mapPlatforms(it.platforms),
        genresDescription = mapGenres(it.genres),
        favorite = false
    )

    private fun getScore(score: Int?) = when {
        score == null -> ScoreLevel.UNKNOWN
        score >= 75 -> ScoreLevel.GREAT
        score >= 50 -> ScoreLevel.AVERAGE
        else -> ScoreLevel.POOR
    }

    private fun mapTags(models: List<TagModel>?) = models
        ?.map { it.slug }
        ?.take(12)

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

    private fun mapList(items: List<String>?) = items?.joinToString(", ")
}
