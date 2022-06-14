package com.rafagnin.gaming.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class GameRepository {
    suspend fun getGames(apiKey: String) = getRetrofit()
        .create(ApiService::class.java)
        .getGames(apiKey)

    suspend fun getUpcomingGames(apiKey: String) = getRetrofit()
        .create(ApiService::class.java)
        .getUpcomingGames(apiKey, getNext12MonthsDate())

    private fun getNext12MonthsDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        val currentDate = sdf.format(calendar.timeInMillis)
        calendar.add(Calendar.YEAR, 1)
        val nextYearDate = sdf.format(calendar.timeInMillis)
        return "$currentDate,$nextYearDate"
    }

    private fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.rawg.io/")
        .addConverterFactory(MoshiConverterFactory.create(
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        ))
        .build()
}
