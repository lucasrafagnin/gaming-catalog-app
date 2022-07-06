package com.rafagnin.gaming.ui.fragment.state

import com.rafagnin.gaming.domain.model.UIGameModel

sealed class ProfileState {
    object Error : ProfileState()
    object Loading : ProfileState()
    data class GamesLoaded(
        val items: List<UIGameModel>?
    ) : ProfileState()
}
