package com.technipixl.evand.movie2.ui

import androidx.lifecycle.ViewModel
import com.technipixl.evand.movie2.Network.APIService
import com.technipixl.evand.movie2.model.MovieResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

open class BaseViewModel: ViewModel() {
	var movies = MutableSharedFlow<List<MovieResult.Movie?>>()
	val service = APIService()
}