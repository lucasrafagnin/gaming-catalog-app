package com.rafagnin.gaming.domain.mapper

import com.rafagnin.gaming.R
import com.rafagnin.gaming.data.model.DeveloperModel
import com.rafagnin.gaming.data.model.GameModel
import com.rafagnin.gaming.data.model.GenreModel
import com.rafagnin.gaming.data.model.PlatformResult
import com.rafagnin.gaming.data.model.TagModel
import com.rafagnin.gaming.domain.model.UIGameDetailModel
import javax.inject.Inject

class GameDetailToDomainMapper @Inject constructor() {

    fun map(it: GameModel) = UIGameDetailModel(
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
        genresDescription = mapGenres(it.genres)
    )

    private fun getScore(score: Int?) = when {
        score == null -> null
        score >= 75 -> R.drawable.bg_great_score
        score >= 50 -> R.drawable.bg_average_score
        else -> R.drawable.bg_poor_score
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
