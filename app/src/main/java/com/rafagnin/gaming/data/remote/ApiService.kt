package com.rafagnin.gaming.data.remote

import com.rafagnin.gaming.data.remote.model.ResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/games")
    suspend fun getGames(
        @Query("key") key: String
    ): Response<ResultModel>

    @GET("api/games")
    suspend fun getUpcomingGames(
        @Query("key") key: String,
        @Query("dates") dates: String,
        @Query("ordering") ordering: String
    ): Response<ResultModel>
}
