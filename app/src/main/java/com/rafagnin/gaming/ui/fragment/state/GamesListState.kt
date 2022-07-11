package com.rafagnin.gaming.ui.fragment.state

import com.rafagnin.gaming.domain.model.GameModel

sealed class GamesListState {
    object Error : GamesListState()
    object Loading : GamesListState()
    data class GamesLoaded(
        val items: List<GameModel>?
    ) : GamesListState()
}
