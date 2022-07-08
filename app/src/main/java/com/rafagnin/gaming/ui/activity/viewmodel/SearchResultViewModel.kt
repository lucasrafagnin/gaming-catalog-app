package com.rafagnin.gaming.ui.activity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.usecase.SearchGames
import com.rafagnin.gaming.ui.activity.state.SearchResultState
import com.rafagnin.gaming.ui.activity.state.SearchResultState.Error
import com.rafagnin.gaming.ui.activity.state.SearchResultState.GamesLoaded
import com.rafagnin.gaming.ui.activity.state.SearchResultState.Loading
import com.rafagnin.gaming.ui.fragment.action.SearchResultAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val searchGames: SearchGames
) : ViewModel() {

    val actionFlow = MutableSharedFlow<SearchResultAction>()
    private val state: MutableStateFlow<SearchResultState> = MutableStateFlow(Loading)
    val _state: StateFlow<SearchResultState> = state

    private var lastSearch = ""

    init {
        viewModelScope.launch {
            handleActions()
        }
    }

    private fun getGames(text: String) = viewModelScope.launch {
        lastSearch = text
        when (val games = searchGames.invoke(query = text)) {
            is Resource.Success -> state.value = GamesLoaded(text, games.data)
            is Resource.Loading -> state.value = Loading
            is Resource.Error -> state.value = Error
        }
    }

    private suspend fun handleActions() {
        actionFlow.collect {
            when (it) {
                is SearchResultAction.Query -> {
                    state.value = Loading
                    getGames(it.text)
                }
                is SearchResultAction.Retry -> {
                    state.value = Loading
                    getGames(lastSearch)
                }
            }
        }
    }
}
