package com.rafagnin.gaming.ui.fragment.action

import com.rafagnin.gaming.domain.model.UIGameDetailModel

sealed class GameDetailAction {
    data class Favorite(
        val model: UIGameDetailModel
    ) : GameDetailAction()
}
