package com.rafagnin.gaming.ui.fragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.usecase.GetUpcomingGames
import com.rafagnin.gaming.ui.fragment.state.UpcomingGamesState
import com.rafagnin.gaming.ui.fragment.state.UpcomingGamesState.Error
import com.rafagnin.gaming.ui.fragment.state.UpcomingGamesState.GamesLoaded
import com.rafagnin.gaming.ui.fragment.state.UpcomingGamesState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingGamesViewModel @Inject constructor(
    private val getUpcomingGames: GetUpcomingGames
) : ViewModel() {

    private val state: MutableStateFlow<UpcomingGamesState> = MutableStateFlow(Loading)
    val _state: StateFlow<UpcomingGamesState> = state

    fun getUpcomingGames() = viewModelScope.launch {
        getUpcomingGames.invoke()
            .catch { state.value = Error }
            .collect {
                when (it) {
                    is Resource.Success -> state.value = GamesLoaded(
                        items = it.data?.results
                    )
                    else -> state.value = Loading
                }
            }
    }
}
