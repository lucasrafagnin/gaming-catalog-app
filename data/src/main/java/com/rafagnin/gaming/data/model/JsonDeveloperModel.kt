package com.rafagnin.gaming.data.model

import com.squareup.moshi.Json

data class JsonDeveloperModel(
    @Json(name = "name") val name: String,
)
