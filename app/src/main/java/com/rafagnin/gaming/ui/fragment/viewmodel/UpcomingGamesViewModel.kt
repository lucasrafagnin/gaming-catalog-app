package com.rafagnin.gaming.ui.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.usecase.GetUpcomingGames
import com.rafagnin.gaming.ui.fragment.state.UpcomingGamesState
import com.rafagnin.gaming.ui.fragment.state.UpcomingGamesState.Error
import com.rafagnin.gaming.ui.fragment.state.UpcomingGamesState.GamesLoaded
import com.rafagnin.gaming.ui.fragment.state.UpcomingGamesState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingGamesViewModel @Inject constructor(
    private val getUpcomingGames: GetUpcomingGames
) : ViewModel() {

    private val _state = MutableLiveData<UpcomingGamesState>()
    val state: LiveData<UpcomingGamesState>
        get() = _state

    fun getUpcomingGames() {
        viewModelScope.launch {
            _state.value = Loading
            getUpcomingGames.invoke()
                .collect {
                    when (it) {
                        is Resource.Success -> _state.value = GamesLoaded(
                            items = it.data?.results
                        )
                        else -> _state.value = Error
                    }
                }
        }
    }
}
