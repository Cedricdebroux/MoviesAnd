package com.technipixl.evand.movie2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.technipixl.evand.movie2.model.MovieResult
import com.technipixl.evand.movie2.ui.SearchViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SearchMovie(
	modifier: Modifier = Modifier,
	onClick: (MovieResult.Movie) -> Unit,
	currentSearch: String?,
) {
	SearchListMovies(onClick = onClick, currentSearch = currentSearch)
}

@Composable
fun SearchListMovies(
	modifier: Modifier = Modifier,
	onClick: (MovieResult.Movie) -> Unit,
	viewModel: SearchViewModel = viewModel(),
	currentSearch: String?
) {

	currentSearch?.let { viewModel.searchMovie(it) }

	val movies by viewModel.movies.collectAsState(initial = listOf())

	if (movies.isNotEmpty()) {

		LazyColumn(modifier = modifier) {
			items(movies) { movie ->
				SearchMovieCell(movie = movie, modifier = Modifier
					.padding(8.dp)
					.clickable {
						onClick(movie)
					})
				Divider()
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
fun SearchHeader(modifier: Modifier = Modifier, currentSearch: (String) -> Unit){
	var textState by remember { mutableStateOf("") }
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
				value = textState,
				onValueChange = {
					textState = it
					currentSearch(textState)
				},
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

