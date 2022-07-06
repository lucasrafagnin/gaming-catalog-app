package com.rafagnin.gaming.ui.fragment.state

import com.rafagnin.gaming.domain.model.UIUpcomingGameModel

sealed class UpcomingGamesState {
    object Loading : UpcomingGamesState()
    object Error : UpcomingGamesState()
    data class GamesLoaded(
        val items: List<UIUpcomingGameModel>?
    ) : UpcomingGamesState()
}
