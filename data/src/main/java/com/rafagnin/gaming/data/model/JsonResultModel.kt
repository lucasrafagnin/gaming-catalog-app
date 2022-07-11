package com.rafagnin.gaming.data.model

import com.squareup.moshi.Json

data class JsonResultModel(
    @Json(name = "results") val results: List<JsonGameModel>
)
