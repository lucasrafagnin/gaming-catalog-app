package com.rafagnin.gaming.domain.mapper

import com.rafagnin.gaming.data.model.GameModel
import com.rafagnin.gaming.domain.model.UIGameModel
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class GameToDomainMapper @Inject constructor() {

    fun map(it: GameModel) = UIGameModel(
        id = it.id,
        name = it.name,
        image = it.image,
        day = mapDate(it.releasedDate, "dd"),
        month = mapDate(it.releasedDate, "MMM"),
    )

    private fun mapDate(date: String, newFormat: String): String {
        val formatOld = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatNew = SimpleDateFormat(newFormat, Locale.getDefault())
        val formatted = formatOld.parse(date)
        return formatNew.format(formatted)
    }
}
