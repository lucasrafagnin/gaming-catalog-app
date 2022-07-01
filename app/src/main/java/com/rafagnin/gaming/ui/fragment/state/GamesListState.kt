package com.rafagnin.gaming.ui.fragment.state

import com.rafagnin.gaming.data.model.GameModel

sealed class GamesListState {
    object Loading : GamesListState()
    data class GamesLoaded(
        val items: List<GameModel>?
    ) : GamesListState()
}
