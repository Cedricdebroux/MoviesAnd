package com.technipixl.evand.movie2.ui

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.technipixl.evand.movie2.Network.SearchAPIServiceImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel() : BaseViewModel() {
    override val service = SearchAPIServiceImpl()
    var currentSearch = MutableStateFlow("")

    override suspend fun getMovies() {
        Log.d("filmSearch", "")
        currentSearch.collectLatest {
            if (it.count() >= 3) {
                delay(1000)
                movies.emit(service.getMoviesResults(service.APIKEY, 1, it))
            }
            else {
                movies.emit(listOf())
            }
        }
    }

    fun searchMovie(searchStr: String) {
        viewModelScope.launch {
            currentSearch.emit(searchStr)
            getMovies()
        }
    }
}