package com.rafagnin.gaming.ui.activity.action

import com.rafagnin.gaming.domain.model.GameDetailModel

sealed class GameDetailAction {
    data class Favorite(
        val model: GameDetailModel
    ) : GameDetailAction()
}
