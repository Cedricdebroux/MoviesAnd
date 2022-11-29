package com.technipixl.evand.movie2.Network

import com.technipixl.evand.movie2.model.MovieResult
import retrofit2.http.GET
import retrofit2.http.Query

class PopularAPIServiceImpl: BaseApiService() {

    interface PopularMovieRoutes{
        @GET("trending/all/day")
        suspend fun trendingMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("page") page: Int
        ) : MovieResult
    }

    suspend fun getTrendingMovies(
        apiKey: String,
        page: Int,
        language: String = "fr"
    ) = retrofit.create(PopularMovieRoutes::class.java)
        .trendingMovies(apiKey, language, page).results
}