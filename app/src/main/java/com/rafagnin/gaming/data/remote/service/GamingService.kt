package com.rafagnin.gaming.data.remote.service

import com.rafagnin.gaming.data.model.GameModel
import com.rafagnin.gaming.data.model.ResultModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GamingService {

    @GET("api/games")
    suspend fun getGames(): ResultModel

    @GET("api/games/{id}")
    suspend fun getGameDetail(
        @Path("id") id: Long
    ): GameModel

    @GET("api/games")
    suspend fun getUpcomingGames(
        @Query("dates") dates: String,
        @Query("ordering") ordering: String
    ): ResultModel
}
