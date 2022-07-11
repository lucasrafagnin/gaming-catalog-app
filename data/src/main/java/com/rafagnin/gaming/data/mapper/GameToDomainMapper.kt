package com.rafagnin.gaming.data.mapper

import com.rafagnin.gaming.data.model.JsonGameModel
import com.rafagnin.gaming.data.model.LocalGameModel
import com.rafagnin.gaming.domain.model.GameDetailModel
import com.rafagnin.gaming.domain.model.GameModel
import javax.inject.Inject

class GameToDomainMapper @Inject constructor() {

    fun map(it: JsonGameModel) = GameModel(
        id = it.id,
        name = it.name,
        image = it.image,
    )

    fun map(it: LocalGameModel) = GameModel(
        id = it.id,
        name = it.name,
        image = it.image,
    )

    fun map(model: GameDetailModel) = LocalGameModel(
        id = model.id,
        name = model.name,
        image = model.image
    )

    fun map(model: GameModel) = LocalGameModel(
        id = model.id,
        name = model.name,
        image = model.image
    )
}
