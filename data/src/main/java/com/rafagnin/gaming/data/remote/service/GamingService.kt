package com.rafagnin.gaming.data.remote.service

import com.rafagnin.gaming.data.model.JsonGameModel
import com.rafagnin.gaming.data.model.JsonResultModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GamingService {

    @GET("api/games")
    suspend fun searchGames(
        @Query("search") search: String?
    ): JsonResultModel

    @GET("api/games")
    suspend fun getGames(): JsonResultModel

    @GET("api/games/{id}")
    suspend fun getGameDetail(
        @Path("id") id: Long
    ): JsonGameModel

    @GET("api/games")
    suspend fun getUpcomingGames(
        @Query("dates") dates: String,
        @Query("ordering") ordering: String
    ): JsonResultModel
}
