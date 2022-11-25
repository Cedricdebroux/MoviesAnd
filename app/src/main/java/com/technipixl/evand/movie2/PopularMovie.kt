package com.technipixl.evand.movie2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.technipixl.evand.movie2.model.MovieResult
import com.technipixl.evand.movie2.ui.PopularViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first

@Composable
fun PopularMovie(
	modifier: Modifier = Modifier,
	onClick: (String, MovieResult.Movie) -> Unit,
	viewModel: PopularViewModel = viewModel()
) {
	viewModel.getMovies()
	val tempMovie = viewModel.movies.collectAsState(initial = listOf<MovieResult.Movie>())
	if (tempMovie.value.isNotEmpty()) {
		tempMovie.value.first()?.let {
			PopularMovieCell(movie = it)
		}
	}
}

@Composable
fun PopularMovieCell(
	modifier: Modifier = Modifier,
	movie: MovieResult.Movie
) {
	Image(
		painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500/${movie.posterPath}"),
		contentDescription = null,
		modifier = modifier.fillMaxSize()
	)
}