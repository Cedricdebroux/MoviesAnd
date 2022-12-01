package com.technipixl.evand.movie2.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.technipixl.evand.movie2.Network.BaseApiService
import com.technipixl.evand.movie2.Network.PopularAPIServiceImpl
import com.technipixl.evand.movie2.model.MovieResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PopularViewModel: BaseViewModel() {

	override val service = PopularAPIServiceImpl()

	fun getDatas(){
		viewModelScope.launch {
			getMovies()
		}
	}

	override suspend fun getMovies() {
		val result = service.getTrendingMovies(service.APIKEY, 1)
		Log.d("film", "$result")
		movies.emit(result)
	}
}