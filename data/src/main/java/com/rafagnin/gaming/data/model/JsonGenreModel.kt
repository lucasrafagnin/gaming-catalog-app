package com.rafagnin.gaming.data.model

import com.squareup.moshi.Json

data class JsonGenreModel(
    @Json(name = "name") val name: String,
)
