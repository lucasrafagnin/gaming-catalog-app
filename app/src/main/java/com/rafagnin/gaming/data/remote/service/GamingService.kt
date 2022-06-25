package com.rafagnin.gaming.data.remote.service

import com.rafagnin.gaming.data.remote.model.ResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GamingService {

    @GET("api/games")
    suspend fun getGames(): Response<ResultModel>

    @GET("api/games")
    suspend fun getUpcomingGames(
        @Query("dates") dates: String,
        @Query("ordering") ordering: String
    ): Response<ResultModel>
}
