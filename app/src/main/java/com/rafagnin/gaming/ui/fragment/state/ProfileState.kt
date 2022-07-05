package com.rafagnin.gaming.ui.fragment.state

import com.rafagnin.gaming.data.model.GameModel

sealed class ProfileState {
    object Error : ProfileState()
    object Loading : ProfileState()
    data class GamesLoaded(
        val items: List<GameModel>?
    ) : ProfileState()
}
