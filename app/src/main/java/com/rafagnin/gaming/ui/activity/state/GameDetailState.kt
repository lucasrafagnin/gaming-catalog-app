package com.rafagnin.gaming.ui.activity.state

import com.rafagnin.gaming.domain.model.GameDetailModel

sealed class GameDetailState {
    object Loading : GameDetailState()
    object Error : GameDetailState()
    data class Loaded(
        val game: GameDetailModel?
    ) : GameDetailState()
}
