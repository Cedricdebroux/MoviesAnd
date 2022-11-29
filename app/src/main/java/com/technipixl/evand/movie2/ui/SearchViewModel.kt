package com.technipixl.evand.movie2.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.technipixl.evand.movie2.Network.BaseApiService
import com.technipixl.evand.movie2.Network.SearchAPIServiceImpl

class SearchViewModel() : BaseViewModel() {
    override val service = SearchAPIServiceImpl()
    var currentSearch = mutableStateOf("")
    override fun getMovies() {

    }
}