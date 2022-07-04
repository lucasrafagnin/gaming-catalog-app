package com.rafagnin.gaming.data.model

import com.squareup.moshi.Json

data class GenreModel(
    @Json(name = "name") val name: String,
)
