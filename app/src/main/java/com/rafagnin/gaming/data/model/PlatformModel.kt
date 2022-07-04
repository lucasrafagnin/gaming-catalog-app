package com.rafagnin.gaming.data.model

import com.squareup.moshi.Json

data class PlatformResult(
    @Json(name = "platform") val platform: PlatformModel
)
data class PlatformModel(
    @Json(name = "name") val name: String,
)
