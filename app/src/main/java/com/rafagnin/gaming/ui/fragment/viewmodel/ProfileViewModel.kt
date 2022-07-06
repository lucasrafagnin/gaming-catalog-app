package com.rafagnin.gaming.ui.fragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.usecase.GetFavoriteGames
import com.rafagnin.gaming.ui.fragment.action.GamesListAction
import com.rafagnin.gaming.ui.fragment.state.ProfileState
import com.rafagnin.gaming.ui.fragment.state.ProfileState.Error
import com.rafagnin.gaming.ui.fragment.state.ProfileState.GamesLoaded
import com.rafagnin.gaming.ui.fragment.state.ProfileState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getFavoriteGames: GetFavoriteGames
) : ViewModel() {

    private val state: MutableStateFlow<ProfileState> = MutableStateFlow(Loading)
    val actionFlow = MutableSharedFlow<GamesListAction>()
    val _state: StateFlow<ProfileState> = state

    init {
        viewModelScope.launch {
            handleActions()
        }
    }

    fun getGames() = viewModelScope.launch(Dispatchers.IO) {
        getFavoriteGames.invoke()
            .catch { state.value = Error }
            .collect {
                when (it) {
                    is Resource.Success -> state.value = GamesLoaded(it.data)
                    is Resource.Loading -> state.value = Loading
                    is Resource.Error -> state.value = Error
                }
            }
    }

    private suspend fun handleActions() {
        actionFlow.collect {
            when (it) {
                GamesListAction.Retry -> {
                    state.value = Loading
                    getGames()
                }
            }
        }
    }
}
