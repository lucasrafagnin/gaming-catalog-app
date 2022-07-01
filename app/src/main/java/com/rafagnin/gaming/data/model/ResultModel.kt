package com.rafagnin.gaming.data.model

import com.squareup.moshi.Json

data class ResultModel(
    @Json(name = "results") val results: List<GameModel>
)
