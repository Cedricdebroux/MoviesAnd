package com.technipixl.evand.movie2.ui

import androidx.lifecycle.ViewModel
import com.technipixl.evand.movie2.Network.BaseApiService
import com.technipixl.evand.movie2.model.MovieResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class BaseViewModel: ViewModel() {
	abstract val service: BaseApiService
	var movies = MutableSharedFlow<List<MovieResult.Movie>>()
	abstract suspend fun getMovies()
}