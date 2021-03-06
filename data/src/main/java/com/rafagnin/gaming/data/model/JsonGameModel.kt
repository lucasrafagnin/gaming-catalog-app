package com.rafagnin.gaming.data.model

import com.squareup.moshi.Json

data class JsonGameModel(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String?,
    @Json(name = "description_raw") val description: String?,
    @Json(name = "website") val website: String?,
    @Json(name = "background_image") val image: String?,
    @Json(name = "metacritic") val metacritic: Int?,
    @Json(name = "released") val releasedDate: String?,
    @Json(name = "tags") val tags: List<JsonTagModel>?,
    @Json(name = "platforms") val platforms: List<PlatformResult>?,
    @Json(name = "genres") val genres: List<JsonGenreModel>?,
    @Json(name = "developers") val developers: List<JsonDeveloperModel>?,
)
