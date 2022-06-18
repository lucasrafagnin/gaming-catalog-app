package com.rafagnin.gaming.ui.fragment.state

import com.rafagnin.gaming.data.remote.model.GameModel

sealed class UpcomingGamesState {
    object Loading: UpcomingGamesState()
    data class GamesLoaded(
        val items: List<GameModel>?
    ): UpcomingGamesState()
}
