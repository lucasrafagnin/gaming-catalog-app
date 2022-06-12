package com.rafagnin.gaming.data.remote.model

import com.squareup.moshi.Json

data class GameModel(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "background_image") val image: String
)
