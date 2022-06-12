package com.rafagnin.gaming.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class GameRepository {
    suspend fun getGames(apiKey: String) = getRetrofit()
        .create(ApiService::class.java)
        .getGames(apiKey)

    private fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.rawg.io/")
        .addConverterFactory(MoshiConverterFactory.create(
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        ))
        .build()
}
