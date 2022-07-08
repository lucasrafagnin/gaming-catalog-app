package com.rafagnin.gaming.domain.usecase

import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.data.GameRepository
import com.rafagnin.gaming.domain.model.UIUpcomingGameModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class GetUpcomingGames @Inject constructor(
    private val repository: GameRepository,
) {

    suspend operator fun invoke(): Resource<List<UIUpcomingGameModel>> {
        return try {
            Resource.Success(repository.getUpcomingGames(getNextYearDate()))
        } catch (exception: Exception) {
            Resource.Error("error")
        }
    }

    private fun getNextYearDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        val currentDate = sdf.format(calendar.timeInMillis)
        calendar.add(Calendar.YEAR, 1)
        val nextYearDate = sdf.format(calendar.timeInMillis)
        return "$currentDate,$nextYearDate"
    }
}
