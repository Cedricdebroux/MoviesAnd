package com.technipixl.evand.movie2.Network

import com.technipixl.evand.movie2.model.MovieResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class APIService {
	private val BASE_URL = "https://api.themoviedb.org/3/"
//	private val BASE_IMG_URL = "https://image.tmdb.org/t/p/w500/"

	private val retrofit = Retrofit.Builder()
		.baseUrl(BASE_URL)
		.addConverterFactory(GsonConverterFactory.create())
		.build()
		.create(MoviesRoutes::class.java)

	private interface MoviesRoutes {
		@GET("trending/all/day")
		suspend fun trendingMovies(
			@Query("api_key") apiKey: String,
			@Query("language") language: String,
			@Query("page") page: Int
		) : Flow<MovieResult>

		@GET("search/movie")
		suspend fun searchResult(
			@Query("api_key") apiKey: String,
			@Query("language") language: String,
			@Query("page") page: Int,
			@Query("query") query: String
		) : MovieResult

		@GET("movie/{id}/similar")
		suspend fun similarMovies(
			@Query("api_key") apiKey: String,
			@Query("language") language: String,
			@Path("id") id: Int
		) : MovieResult
	}

	suspend fun getTrendingMovies(
		apiKey: String,
		page: Int,
		language: String = "fr"
	) = retrofit.trendingMovies(apiKey, language, page).map {
		it.results
	}

	suspend fun getSearchResult(
		apiKey: String,
		page: Int,
		query: String,
		language: String = "fr",
	) = retrofit.searchResult(apiKey, language, page, query)

	suspend fun getSimilarMovies(
		apiKey: String,
		id: Int,
		language: String = "fr",
	) = retrofit.similarMovies(apiKey, language, id)
}

