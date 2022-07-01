package com.rafagnin.gaming.ui.fragment.state

import com.rafagnin.gaming.data.model.GameModel

sealed class UpcomingGamesState {
    object Loading : UpcomingGamesState()
    object Error : UpcomingGamesState()
    data class GamesLoaded(
        val items: List<GameModel>?
    ) : UpcomingGamesState()
}
