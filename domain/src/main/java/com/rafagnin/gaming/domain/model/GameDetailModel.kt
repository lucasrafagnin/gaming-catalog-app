package com.rafagnin.gaming.domain.model

import com.rafagnin.gaming.domain.util.ScoreLevel

data class GameDetailModel(
    val id: Long,
    val name: String?,
    val image: String?,
    val website: String?,
    val description: String?,
    val releaseDate: String?,
    val score: Int?,
    val scoreBg: ScoreLevel,
    val tags: List<String>?,
    val developersDescription: String?,
    val platformsDescription: String?,
    val genresDescription: String?,
    val favorite: Boolean
)
