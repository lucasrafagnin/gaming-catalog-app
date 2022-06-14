package com.rafagnin.gaming.ui.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafagnin.gaming.BuildConfig
import com.rafagnin.gaming.data.remote.model.GameModel
import com.rafagnin.gaming.data.remote.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpcomingGamesViewModel : ViewModel() {

    private val repository = GameRepository()

    private val _items = MutableLiveData<List<GameModel>>()
    val items: LiveData<List<GameModel>>
        get() = _items

    fun getUpcomingGames() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = repository.getUpcomingGames(BuildConfig.RAWG_API_KEY)

            if (res.isSuccessful) {
                withContext(Dispatchers.Main) {
                    _items.value = res.body()?.results
                }
            }
        }
    }
}
