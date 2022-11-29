package com.technipixl.evand.movie2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.technipixl.evand.movie2.model.MovieResult
import com.technipixl.evand.movie2.ui.PopularViewModel
import com.technipixl.evand.movie2.ui.SearchViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import java.text.DateFormat
import android.util.Log
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

@Composable
fun SearchMovie(
	modifier: Modifier = Modifier,
	onClick: (MovieResult.Movie) -> Unit,
) {
	SearchListMovies(onClick = onClick)
}

@Composable
fun SearchListMovies(
	modifier: Modifier = Modifier,
	onClick: (MovieResult.Movie) -> Unit,
	viewModel: SearchViewModel = viewModel()
) {


	val movies by viewModel.movies.collectAsState(initial = listOf<MovieResult.Movie>())

	if (movies.isNotEmpty()) {

		LazyColumn(modifier = modifier) {
			items(movies.size) { index ->
				movies[index]?.let {
					SearchMovieCell(movie = it, modifier = Modifier
						.padding(8.dp)
						.clickable {
						onClick(it)
					})
					Divider()
				}
			}
		}
	}
}


@Composable
fun SearchMovieCell(movie: MovieResult.Movie, modifier: Modifier = Modifier){


	Row(modifier = modifier) {
		Image(
			modifier = Modifier
				.fillMaxWidth(0.20f)
				.height(100.dp),
			painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500/${movie.posterPath}"),
			contentDescription = null,
			contentScale = ContentScale.FillHeight
		)
		Column {
			movie.title?.let {
				Text(
					text = it,
					fontWeight = FontWeight.Bold
				) }
			movie.releaseDate?.let {
				Text(it)
			}
		}
	}
}

@Composable
@Preview
fun SearchHeader(modifier: Modifier = Modifier){
	var textState by remember {
		mutableStateOf(TextFieldValue(""))
	}
	Box(modifier = modifier.background(MaterialTheme.colors.primary, shape = RoundedCornerShape(size = 20.dp))) {
		Column(
			modifier = Modifier
				.padding(top = 15.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)) {
			Text(text = "Search", fontSize = 30.sp, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(5.dp))
			TextField(
				leadingIcon = {
					Icon(Icons.Filled.Search, "Recherche", tint = Color.Black)
				},
				placeholder = {
					Text(text = "Search")
				},
				value = textState, onValueChange = {textState = it},
				modifier = Modifier
					.fillMaxWidth(1F)
					.padding(5.dp),
				shape = RoundedCornerShape(size = 50.dp),

				colors = TextFieldDefaults.textFieldColors(
					backgroundColor = Color.White,
					cursorColor = Color.Black,
					unfocusedIndicatorColor = Color.Transparent,
					focusedIndicatorColor = Color.Transparent
				)
			)
		}
	}
}

