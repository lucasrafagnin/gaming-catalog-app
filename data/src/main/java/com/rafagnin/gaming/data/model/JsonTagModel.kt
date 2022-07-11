package com.rafagnin.gaming.data.model

import com.squareup.moshi.Json

data class JsonTagModel(
    @Json(name = "slug") val slug: String,
)
