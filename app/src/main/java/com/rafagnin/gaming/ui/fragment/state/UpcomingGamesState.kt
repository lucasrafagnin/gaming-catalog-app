package com.rafagnin.gaming.ui.fragment.state

import com.rafagnin.gaming.domain.model.UIGameModel

sealed class UpcomingGamesState {
    object Loading : UpcomingGamesState()
    object Error : UpcomingGamesState()
    data class GamesLoaded(
        val items: List<UIGameModel>?
    ) : UpcomingGamesState()
}
