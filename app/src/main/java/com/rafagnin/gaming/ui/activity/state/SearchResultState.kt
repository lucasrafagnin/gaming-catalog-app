package com.rafagnin.gaming.ui.activity.state

import com.rafagnin.gaming.data.model.GameModel

sealed class SearchResultState {
    object Error : SearchResultState()
    object Loading : SearchResultState()
    data class GamesLoaded(
        val query: String,
        val items: List<GameModel>?
    ) : SearchResultState()
}
