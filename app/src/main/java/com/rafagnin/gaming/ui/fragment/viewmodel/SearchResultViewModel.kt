package com.rafagnin.gaming.ui.fragment.viewmodel

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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val searchGames: SearchGames
) : ViewModel() {

    val actionFlow = MutableSharedFlow<SearchResultAction>()
    private val state: MutableStateFlow<SearchResultState> = MutableStateFlow(Loading)
    val _state: StateFlow<SearchResultState> = state

    init {
        viewModelScope.launch {
            handleActions()
        }
    }

    private fun getGames(text: String) = viewModelScope.launch {
        searchGames.invoke(query = text)
            .catch { state.value = Error }
            .collect {
                when (it) {
                    is Resource.Success -> state.value = GamesLoaded(it.data?.results)
                    is Resource.Loading -> state.value = Loading
                    is Resource.Error -> state.value = Error
                }
            }
    }

    private suspend fun handleActions() {
        actionFlow.collect {
            when (it) {
                is SearchResultAction.Query -> {
                    state.value = Loading
                    getGames(it.text)
                }
            }
        }
    }
}
