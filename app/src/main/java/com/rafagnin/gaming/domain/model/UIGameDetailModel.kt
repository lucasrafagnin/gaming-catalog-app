package com.rafagnin.gaming.domain.model

data class UIGameDetailModel(
    val name: String,
    val image: String,
    val website: String,
    val description: String,
    val releaseDate: String,
    val developersDescription: String?,
    val platformsDescription: String?,
    val genresDescription: String?,
)
