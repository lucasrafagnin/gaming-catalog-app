package com.rafagnin.gaming.domain.mapper

import com.rafagnin.gaming.data.model.GameModel
import com.rafagnin.gaming.data.model.LocalGameModel
import com.rafagnin.gaming.domain.model.UIGameDetailModel
import com.rafagnin.gaming.domain.model.UIGameModel
import javax.inject.Inject

class GameToDomainMapper @Inject constructor() {

    fun map(it: GameModel) = UIGameModel(
        id = it.id,
        name = it.name,
        image = it.image,
    )

    fun map(it: LocalGameModel) = UIGameModel(
        id = it.id,
        name = it.name,
        image = it.image,
    )

    fun map(model: UIGameDetailModel) = LocalGameModel(
        id = model.id,
        name = model.name,
        image = model.image
    )
}
