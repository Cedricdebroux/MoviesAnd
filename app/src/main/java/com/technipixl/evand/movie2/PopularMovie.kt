package com.technipixl.evand.movie2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.technipixl.evand.movie2.model.MovieResult
import com.technipixl.evand.movie2.ui.PopularViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.size.Scale
import kotlinx.coroutines.flow.first
import retrofit2.http.Header

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PopularMovie(
	modifier: Modifier = Modifier,
	onClick: (MovieResult.Movie) -> Unit,
	viewModel: PopularViewModel = viewModel()
) {
	val refreshing by viewModel.isRefreshing.collectAsState()
	val pullRefreshState = rememberPullRefreshState(refreshing = refreshing, {viewModel.refreshing()})
	val movies by viewModel.movies.collectAsState(initial = listOf<MovieResult.Movie>())
	
		viewModel.getMovies()
		if (movies.isNotEmpty()) {
			Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
				LazyVerticalGrid(columns = GridCells.Fixed(3)) {
					items(movies.size) { index ->
						movies[index]?.let {
							PopularMovieCell(movie = it, modifier = Modifier.clickable {
								onClick(it)
							})
						}
					}
				}
				PullRefreshIndicator(
					refreshing = refreshing, state = pullRefreshState, modifier = Modifier.align(
						Alignment.TopCenter
					)
				)
			}
		}
}

@Composable
fun PopularHeader(modifier: Modifier = Modifier){
	Column(modifier
		.fillMaxWidth()
		.background(Color.White),
		horizontalAlignment =  Alignment.CenterHorizontally
	) {
		Text(text = stringResource(R.string.popular_movies_titles), fontWeight = FontWeight.Bold)
	}
}

@Composable
fun PopularMovieCell(
	modifier: Modifier = Modifier,
	movie: MovieResult.Movie
) {
		Card(
			shape = RoundedCornerShape(20),
			modifier = modifier
				.fillMaxWidth()
				.height(205.dp)
				.padding(8.dp),
			elevation = 5.dp
		) {
			Image(
				painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500/${movie.posterPath}"),
				contentDescription = null,
				contentScale = ContentScale.FillHeight
			)
			RatingCircle(rating = movie.voteAverage, modifier = Modifier.size(35.dp), textSize = 12.sp)
		}
}

@Composable
fun RatingCircle(modifier: Modifier = Modifier, rating: Double?, textSize: TextUnit, fontWeight: FontWeight = FontWeight.Normal){
	Column(modifier = Modifier
		.fillMaxWidth()
		.padding(10.dp), horizontalAlignment = Alignment.End) {
		Box(
			modifier = modifier
				.clip(CircleShape)
				.background(Color.White)
				.border(2.dp, MaterialTheme.colors.primary, shape = CircleShape),
		contentAlignment = Alignment.Center
		){
			Text(
				text = String.format("%.1f", rating),
			fontSize = textSize,
			fontWeight = fontWeight
			)
		}

	}

}