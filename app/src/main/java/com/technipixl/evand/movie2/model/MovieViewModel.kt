package com.technipixl.evand.movie2.model

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.technipixl.evand.movie2.Network.APIService
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

	var service = APIService()
	lateinit var movies: MutableStateFlow<MovieResult.Movie>

	fun getMovies() {
		viewModelScope.launch {
					service.getTrendingMovies("b2168bae3a2c67509eb6b97572f521c2", 1).first()?.forEach {
						it?.let {
							movies.emit(it)
						}
					}

		}

		//movies.collectAsState()
	}
}