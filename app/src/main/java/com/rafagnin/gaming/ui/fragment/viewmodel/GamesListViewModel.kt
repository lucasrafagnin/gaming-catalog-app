package com.rafagnin.gaming.ui.fragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.usecase.GetAllGames
import com.rafagnin.gaming.ui.fragment.state.GamesListState
import com.rafagnin.gaming.ui.fragment.state.GamesListState.Error
import com.rafagnin.gaming.ui.fragment.state.GamesListState.GamesLoaded
import com.rafagnin.gaming.ui.fragment.state.GamesListState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesListViewModel @Inject constructor(
    private val getAllGames: GetAllGames
) : ViewModel() {

    private val state: MutableStateFlow<GamesListState> = MutableStateFlow(Loading)
    val _state: StateFlow<GamesListState> = state

    fun getGames() = viewModelScope.launch {
        getAllGames.invoke()
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
