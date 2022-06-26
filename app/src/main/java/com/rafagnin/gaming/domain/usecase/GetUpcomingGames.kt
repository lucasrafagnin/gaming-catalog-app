package com.rafagnin.gaming.domain.usecase

import com.rafagnin.gaming.data.repository.GameRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GetUpcomingGames @Inject constructor(
    private val repository: GameRepository
) {

    suspend operator fun invoke() = repository.getUpcomingGames(
        limitDate = getNextYearDate()
    )

    private fun getNextYearDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        val currentDate = sdf.format(calendar.timeInMillis)
        calendar.add(Calendar.YEAR, 1)
        val nextYearDate = sdf.format(calendar.timeInMillis)
        return "$currentDate,$nextYearDate"
    }
}
