package com.rafagnin.gaming.data.mapper

import com.rafagnin.gaming.data.model.GameModel
import com.rafagnin.gaming.domain.model.UIUpcomingGameModel
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class UpcomingGameToDomainMapper @Inject constructor() {

    fun map(it: GameModel) = UIUpcomingGameModel(
        id = it.id,
        name = it.name,
        image = it.image,
        day = mapDate(it.releasedDate, "dd"),
        month = mapDate(it.releasedDate, "MMM"),
    )

    private fun mapDate(date: String?, newFormat: String): String? {
        if (date == null) return null
        val formatOld = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatNew = SimpleDateFormat(newFormat, Locale.getDefault())
        val formatted = formatOld.parse(date)
        return formatNew.format(formatted)
    }
}
