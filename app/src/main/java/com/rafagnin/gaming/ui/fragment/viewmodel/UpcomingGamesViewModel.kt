package com.rafagnin.gaming.ui.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafagnin.gaming.domain.usecase.GetUpcomingGames
import com.rafagnin.gaming.ui.fragment.state.UpcomingGamesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UpcomingGamesViewModel @Inject constructor(
    private val getUpcomingGames: GetUpcomingGames
) : ViewModel() {

    private val _state = MutableLiveData<UpcomingGamesState>()
    val state: LiveData<UpcomingGamesState>
        get() = _state

    fun getUpcomingGames() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getUpcomingGames.invoke()

            if (res.isSuccessful) {
                withContext(Dispatchers.Main) {
                    _state.value = UpcomingGamesState.GamesLoaded(
                        items = res.body()?.results
                    )
                }
            }
        }
    }
}
