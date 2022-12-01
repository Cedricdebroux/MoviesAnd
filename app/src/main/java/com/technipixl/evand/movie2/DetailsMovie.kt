package com.technipixl.evand.movie2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.technipixl.evand.movie2.model.MovieResult
import com.technipixl.evand.movie2.ui.DetailViewMode

@Composable
fun DetailsMovie(
	modifier: Modifier = Modifier,
	onClick: (MovieResult.Movie) -> Unit,
	movie: MovieResult.Movie,
	viewModel: DetailViewMode = viewModel()
) {
	val scrollState = rememberScrollState()
	viewModel.currentMovie = movie

	Box(modifier = modifier) {
		Image(
			painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500/${viewModel.currentMovie.backdropPath}"),
			contentDescription = null,
			contentScale = ContentScale.FillHeight,
			modifier = Modifier
				.fillMaxWidth()
				.fillMaxHeight(0.35f)
				.offset(y = (-48).dp)
		)
		Column(
			Modifier
				.fillMaxWidth()
				.padding(horizontal = 25.dp)
		) {
			Spacer(modifier = Modifier.fillMaxHeight(0.22f))
			with(viewModel.currentMovie) {
				TitleRow(title = title, rating = voteAverage, posterPath = posterPath)
			}
			Spacer(modifier = Modifier.height(20.dp))
			Text(text = "Synopsis", fontWeight = FontWeight.Bold)
			Spacer(modifier = Modifier.height(8.dp))
			Text(
				modifier = Modifier
					.fillMaxWidth()
					.fillMaxHeight(0.30f)
					.verticalScroll(scrollState),
				text = viewModel.currentMovie.overview ?: ""
			)
			Spacer(Modifier.height(8.dp))
		}
	}
}

@Composable
fun SimilarMovieList(
	modifier: Modifier = Modifier,
	movies: List<MovieResult.Movie>,
	onClick: (MovieResult.Movie) -> Unit
) {
	LazyRow {
		items(movies) { movie ->
			SimilarMovieCell(movie = movie) {
				onClick(movie)
			}
		}
	}
}

@Composable
fun SimilarMovieCell(
	modifier: Modifier = Modifier,
	movie: MovieResult.Movie,
	onClick: () -> Unit
) {
	Column(modifier = modifier) {
		Card {
			Image(
				painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500/${movie.posterPath}"),
				contentDescription = null,
				contentScale = ContentScale.FillHeight
			)
			Text(text = movie.title ?: "", fontWeight = FontWeight.Bold, maxLines = 2)
		}
	}
}

@Composable
fun TitleRow(
	modifier: Modifier = Modifier,
	title: String?,
	rating: Double?,
	posterPath: String?
) {
	Box(
		modifier = modifier
			.height(200.dp)
			.fillMaxWidth(),
	) {
		Row(
			verticalAlignment = Alignment.Bottom
		) {
			Card(modifier = Modifier
				.fillMaxHeight()
				.width(120.dp)) {
				Image(
					painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500/${posterPath}"),
					contentDescription = null,
					contentScale = ContentScale.FillHeight
				)
			}
			title?.let {
				Text(
					it,
					maxLines = 2,
					fontWeight = FontWeight.Bold,
					modifier = Modifier
						.padding(15.dp),
					fontSize = 30.sp
				)
			}
		}
		Box(contentAlignment = Alignment.TopEnd,){

			RatingCircle(
				rating = rating,
				modifier = Modifier.size(60.dp),
				textSize = 25.sp,
				fontWeight = FontWeight.Bold
			)
		}

	}
}

@Composable
fun DetailHeader(onClick: () -> Unit){
	Row(verticalAlignment = Alignment.CenterVertically) {
		IconButton(onClick = onClick) {
			Icon(
				imageVector = Icons.Filled.ArrowBackIos,
				contentDescription = null,
				tint = Color.Blue
			)
		}
		Text(modifier = Modifier.offset(x = (-15).dp), text = "Back", color = Color.Blue)
	}
}

@Composable
@Preview
fun HeaderPreview() {
	DetailHeader {

	}
}

@Composable
@Preview(showSystemUi = true)
fun DetailsPreview() {
	DetailsMovie(onClick = {}, movie = MovieResult.Movie(
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
	)
	)
}