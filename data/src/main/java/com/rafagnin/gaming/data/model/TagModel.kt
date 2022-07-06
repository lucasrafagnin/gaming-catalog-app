package com.rafagnin.gaming.data.model

import com.squareup.moshi.Json

data class TagModel(
    @Json(name = "slug") val slug: String,
)
