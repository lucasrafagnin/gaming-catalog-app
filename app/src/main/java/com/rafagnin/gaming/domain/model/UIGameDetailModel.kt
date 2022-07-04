package com.rafagnin.gaming.domain.model

data class UIGameDetailModel(
    val name: String,
    val image: String,
    val website: String,
    val description: String,
    val releaseDate: String,
    val developers: List<String>?,
    val platforms: List<String>?,
    val genres: List<String>?,
)
