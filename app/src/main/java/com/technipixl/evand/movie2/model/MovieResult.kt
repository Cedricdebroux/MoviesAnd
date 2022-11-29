package com.technipixl.evand.movie2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieResult(
	val page: Int?, // 1
	val results: List<Movie>?,
	@SerializedName("total_pages")
	val totalPages: Int?, // 1000
	@SerializedName("total_results")
	val totalResults: Int? // 20000
) {
	data class Movie(
		@SerializedName("backdrop_path")
		val backdropPath: String?, // /bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg
		val id: Int?, // 436270
		val name: String?, // Mercredi
		val overview: String?, // Près de 5000 ans après avoir été doté des pouvoirs tout puissants des dieux égyptiens – et emprisonné très rapidement après – Black Adam est libéré de sa tombe terrestre, prêt à faire régner sa forme unique de justice dans le monde moderne.
		@SerializedName("poster_path")
		val posterPath: String?, // /n0ThzfyEMdfn8tThjehMB4N8zqr.jpg
		@SerializedName("release_date")
		val releaseDate: String?, // 2022-10-19
		val title: String?, // Black Adam
		@SerializedName("vote_average")
		val voteAverage: Double?, // 7.039
		@SerializedName("genre_ids")
		val genreIds: List<Int>?,
	) : Serializable
}