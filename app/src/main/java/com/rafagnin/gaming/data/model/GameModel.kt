package com.rafagnin.gaming.data.model

import com.squareup.moshi.Json

data class GameModel(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String?,
    @Json(name = "website") val website: String?,
    @Json(name = "background_image") val image: String,
    @Json(name = "released") val releasedDate: String
)
