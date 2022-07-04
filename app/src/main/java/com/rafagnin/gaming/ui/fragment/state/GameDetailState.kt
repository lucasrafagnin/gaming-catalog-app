package com.rafagnin.gaming.ui.fragment.state

import com.rafagnin.gaming.domain.model.UIGameDetailModel

sealed class GameDetailState {
    object Loading : GameDetailState()
    object Error : GameDetailState()
    data class Loaded(
        val game: UIGameDetailModel?
    ) : GameDetailState()
}
