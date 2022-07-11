package com.rafagnin.gaming.data.mapper

import com.rafagnin.gaming.data.common.DateHelper
import com.rafagnin.gaming.data.model.JsonGameModel
import com.rafagnin.gaming.domain.model.UpcomingGameModel
import javax.inject.Inject

class UpcomingGameToDomainMapper @Inject constructor() {

    fun map(it: GameModel) = UIUpcomingGameModel(
        id = it.id,
        name = it.name,
        image = it.image,
        day = DateHelper.mapDate(it.releasedDate, "dd"),
        month = DateHelper.mapDate(it.releasedDate, "MMM"),
    )
}
