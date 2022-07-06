package com.rafagnin.gaming.ui.activity.state

import com.rafagnin.gaming.domain.model.UIGameModel

sealed class SearchResultState {
    object Error : SearchResultState()
    object Loading : SearchResultState()
    data class GamesLoaded(
        val query: String,
        val items: List<UIGameModel>?
    ) : SearchResultState()
}
