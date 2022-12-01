package com.technipixl.evand.movie2.ui

import com.technipixl.evand.movie2.Network.BaseApiService
import com.technipixl.evand.movie2.Network.SimilarAPIServiceImpl
import com.technipixl.evand.movie2.model.MovieResult

class DetailViewMode : BaseViewModel() {
	override val service = SimilarAPIServiceImpl()

	lateinit var currentMovie: MovieResult.Movie

	override suspend fun getMovies() {

	}
}