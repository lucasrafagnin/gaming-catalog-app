package com.rafagnin.gaming.ui.fragment.state

import com.rafagnin.gaming.domain.model.GameModel

sealed class ProfileState {
    object Error : ProfileState()
    object Loading : ProfileState()
    object Empty : ProfileState()
    data class GamesLoaded(
        val items: List<GameModel>?
    ) : ProfileState()
}
