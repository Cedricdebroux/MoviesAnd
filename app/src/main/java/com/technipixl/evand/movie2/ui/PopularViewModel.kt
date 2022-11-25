package com.technipixl.evand.movie2.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.technipixl.evand.movie2.Network.APIService
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PopularViewModel: BaseViewModel() {
	fun getMovies(){
		viewModelScope.launch {
			service.getTrendingMovies(APIService.APIKEY, 1)?.let {
				movies.emit(it)
				Log.d("film", "$it")
			}
		}
	}
}