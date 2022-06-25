package com.rafagnin.gaming.ui.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafagnin.gaming.domain.usecase.GetAllGames
import com.rafagnin.gaming.ui.fragment.state.GamesListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GamesListViewModel @Inject constructor(
    private val getAllGames: GetAllGames
) : ViewModel() {

    private val _state = MutableLiveData<GamesListState>()
    val state: LiveData<GamesListState>
        get() = _state

    fun getGames() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getAllGames.invoke()

            if (res.isSuccessful) {
                withContext(Dispatchers.Main) {
                    _state.value = GamesListState.GamesLoaded(
                        items = res.body()?.results
                    )
                }
            }
        }
    }
}
