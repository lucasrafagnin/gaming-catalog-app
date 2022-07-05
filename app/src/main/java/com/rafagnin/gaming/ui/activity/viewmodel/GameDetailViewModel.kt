package com.rafagnin.gaming.ui.activity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.usecase.GetGameDetail
import com.rafagnin.gaming.ui.activity.state.GameDetailState
import com.rafagnin.gaming.ui.activity.state.GameDetailState.Error
import com.rafagnin.gaming.ui.activity.state.GameDetailState.Loaded
import com.rafagnin.gaming.ui.activity.state.GameDetailState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val getGameDetail: GetGameDetail
) : ViewModel() {

    private val state: MutableStateFlow<GameDetailState> = MutableStateFlow(Loading)
    val _state: StateFlow<GameDetailState> = state

    fun getGameDetail(id: Long) = viewModelScope.launch {
        getGameDetail.invoke(id)
            .catch { state.value = Error }
            .collect {
                when (it) {
                    is Resource.Success -> state.value = Loaded(it.data)
                    is Resource.Loading -> state.value = Loading
                    is Resource.Error -> state.value = Error
                }
            }
    }
}
