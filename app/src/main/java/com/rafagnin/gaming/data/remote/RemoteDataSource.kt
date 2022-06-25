package com.rafagnin.gaming.data.remote

import com.rafagnin.gaming.data.remote.service.GamingService
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val gamingService: GamingService
) {
    suspend fun getGames() = gamingService.getGames()

    suspend fun getUpcomingGames() =
        gamingService.getUpcomingGames(
            dates = getNext12MonthsDate(),
            ordering = "released"
        )

    private fun getNext12MonthsDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        val currentDate = sdf.format(calendar.timeInMillis)
        calendar.add(Calendar.YEAR, 1)
        val nextYearDate = sdf.format(calendar.timeInMillis)
        return "$currentDate,$nextYearDate"
    }
}
