package com.rafagnin.gaming.ui.activity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafagnin.gaming.domain.Resource
import com.rafagnin.gaming.domain.model.UIGameDetailModel
import com.rafagnin.gaming.domain.usecase.FavoriteGame
import com.rafagnin.gaming.domain.usecase.GetGameDetail
import com.rafagnin.gaming.ui.activity.state.GameDetailState
import com.rafagnin.gaming.ui.activity.state.GameDetailState.Error
import com.rafagnin.gaming.ui.activity.state.GameDetailState.Loaded
import com.rafagnin.gaming.ui.activity.state.GameDetailState.Loading
import com.rafagnin.gaming.ui.fragment.action.GameDetailAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val getGameDetail: GetGameDetail,
    private val favoriteGame: FavoriteGame
) : ViewModel() {

    private val state: MutableStateFlow<GameDetailState> = MutableStateFlow(Loading)
    val _state: StateFlow<GameDetailState> = state

    val actionFlow = MutableSharedFlow<GameDetailAction>()

    init {
        viewModelScope.launch {
            handleActions()
        }
    }

    fun getGameDetail(id: Long) = viewModelScope.launch {
        when (val detail = getGameDetail.invoke(id)) {
            is Resource.Success -> state.value = Loaded(detail.data)
            is Resource.Loading -> state.value = Loading
            is Resource.Error -> state.value = Error
        }
    }

    private fun favoriteGame(model: UIGameDetailModel) = viewModelScope.launch(Dispatchers.IO) {
        favoriteGame.invoke(model, !model.favorite)
    }

    private suspend fun handleActions() {
        actionFlow.collect {
            when (it) {
                is GameDetailAction.Favorite -> favoriteGame(it.model)
            }
        }
    }
}
