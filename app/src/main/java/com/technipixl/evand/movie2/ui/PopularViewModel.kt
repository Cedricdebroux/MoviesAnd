package com.technipixl.evand.movie2.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.technipixl.evand.movie2.Network.BaseApiService
import com.technipixl.evand.movie2.Network.PopularAPIServiceImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PopularViewModel: BaseViewModel() {

	override val service = PopularAPIServiceImpl()
	var isRefreshing = MutableStateFlow(false)
	fun refreshing(){
		viewModelScope.launch {
			isRefreshing.emit(true)
		}
		getMovies()
	}
	override fun getMovies(){

		viewModelScope.launch {
			service.getTrendingMovies(service.APIKEY, 1)?.let {
				movies.emit(it)
				Log.d("film", "$it")
				isRefreshing.emit(false)
			}
		}
	}
}