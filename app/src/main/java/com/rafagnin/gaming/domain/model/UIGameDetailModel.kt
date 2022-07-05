package com.rafagnin.gaming.domain.model

import androidx.annotation.DrawableRes

data class UIGameDetailModel(
    val name: String?,
    val image: String?,
    val website: String?,
    val description: String?,
    val releaseDate: String?,
    val score: Int?,
    @DrawableRes val scoreBg: Int?,
    val tags: List<String>?,
    val developersDescription: String?,
    val platformsDescription: String?,
    val genresDescription: String?,
)
