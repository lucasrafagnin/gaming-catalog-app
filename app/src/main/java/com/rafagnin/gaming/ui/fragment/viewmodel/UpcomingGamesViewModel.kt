package com.rafagnin.gaming.ui.fragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.usecase.GetUpcomingGames
import com.rafagnin.gaming.ui.fragment.action.GamesListAction
import com.rafagnin.gaming.ui.fragment.state.UpcomingGamesState
import com.rafagnin.gaming.ui.fragment.state.UpcomingGamesState.Error
import com.rafagnin.gaming.ui.fragment.state.UpcomingGamesState.GamesLoaded
import com.rafagnin.gaming.ui.fragment.state.UpcomingGamesState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingGamesViewModel @Inject constructor(
    private val getUpcomingGames: GetUpcomingGames
) : ViewModel() {

    val actionFlow = MutableSharedFlow<GamesListAction>()
    private val state: MutableStateFlow<UpcomingGamesState> = MutableStateFlow(Loading)
    val _state: StateFlow<UpcomingGamesState> = state

    init {
        getUpcomingGames()
        viewModelScope.launch {
            handleActions()
        }
    }

    private fun getUpcomingGames() = viewModelScope.launch {
        when (val games = getUpcomingGames.invoke()) {
            is Resource.Success -> state.value = GamesLoaded(games.data)
            is Resource.Loading -> state.value = Loading
            is Resource.Error -> state.value = Error
        }
    }

    private suspend fun handleActions() {
        actionFlow.collect {
            when (it) {
                GamesListAction.Retry -> {
                    state.value = Loading
                    getUpcomingGames()
                }
            }
        }
    }
}
