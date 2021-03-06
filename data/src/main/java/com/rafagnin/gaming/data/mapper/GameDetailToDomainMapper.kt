package com.rafagnin.gaming.data.mapper

import com.rafagnin.gaming.data.model.JsonDeveloperModel
import com.rafagnin.gaming.data.model.JsonGameModel
import com.rafagnin.gaming.data.model.JsonGenreModel
import com.rafagnin.gaming.data.model.PlatformResult
import com.rafagnin.gaming.data.model.JsonTagModel
import com.rafagnin.gaming.domain.model.GameDetailModel
import com.rafagnin.gaming.domain.util.ScoreLevel
import javax.inject.Inject

class GameDetailToDomainMapper @Inject constructor() {

    fun map(it: JsonGameModel) = GameDetailModel(
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

    private fun mapTags(models: List<JsonTagModel>?) = models
        ?.map { it.slug }
        ?.take(12)

    private fun mapPlatforms(models: List<PlatformResult>?) = models
        ?.map { it.platform }
        ?.map { it.name }
        .run { mapList(this) }

    private fun mapGenres(models: List<JsonGenreModel>?) = models
        ?.map { it.name }
        .run { mapList(this) }

    private fun mapDevelopers(models: List<JsonDeveloperModel>?) = models
        ?.map { it.name }
        .run { mapList(this) }

    private fun mapList(items: List<String>?) = items?.joinToString(", ")
}
