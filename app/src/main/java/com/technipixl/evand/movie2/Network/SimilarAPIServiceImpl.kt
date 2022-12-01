package com.technipixl.evand.movie2.Network

import com.technipixl.evand.movie2.model.MovieResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class SimilarAPIServiceImpl:BaseApiService() {
    interface SimilarMovieRoutes{
        @GET("movie/{id}/similar")
        suspend fun similarMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Path("id") id: Int
        ) : MovieResult
    }
    suspend fun getSimilarMovies(
        apiKey: String,
        id: Int,
        language: String = "fr",
    ) = retrofit.create(SimilarMovieRoutes::class.java)
        .similarMovies(apiKey, language, id).results
}