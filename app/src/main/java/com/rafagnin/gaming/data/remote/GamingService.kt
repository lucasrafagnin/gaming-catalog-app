package com.rafagnin.gaming.data.remote

import com.rafagnin.gaming.BuildConfig
import com.rafagnin.gaming.data.remote.model.ResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GamingService {

    @GET("api/games")
    suspend fun getGames(
        @Query("key") key: String = BuildConfig.RAWG_API_KEY
    ): Response<ResultModel>

    @GET("api/games")
    suspend fun getUpcomingGames(
        @Query("key") key: String = BuildConfig.RAWG_API_KEY,
        @Query("dates") dates: String,
        @Query("ordering") ordering: String
    ): Response<ResultModel>
}
