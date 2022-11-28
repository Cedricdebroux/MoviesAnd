package com.technipixl.evand.movie2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.technipixl.evand.movie2.model.MovieResult

@Composable
fun SearchMovie(
	modifier: Modifier = Modifier,
	onClick: (MovieResult.Movie) -> Unit,
) {

}

//@Composable
//fun SearchListMovies(modifier: Modifier = Modifier){
//    LazyColumn {
//        items(movies) {
//            SearchCell(item = it)
//        }
//    }
//}

@Composable
fun SearchCell(item: MovieResult.Movie, modifier: Modifier = Modifier){

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

