package com.technipixl.evand.movie2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.technipixl.evand.movie2.model.MovieResult

@Composable
fun DetailsMovie(
	modifier: Modifier = Modifier,
	onClick: (MovieResult.Movie) -> Unit,
	movie: MovieResult.Movie,
) {
	Column(modifier = modifier) {
		Image(
			painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500/${movie.backdropPath}"),
			contentDescription = null,
			contentScale = ContentScale.FillHeight,
			modifier = Modifier
				.fillMaxWidth()
				.fillMaxHeight(0.35f)
				.offset(y = (-25).dp)
		)
		Box(
			modifier = Modifier
				.offset(y = (-85).dp)
				.height(200.dp)
				.fillMaxWidth()
				.padding(horizontal = 25.dp),
		) {
			Row(
				verticalAlignment = Alignment.Bottom
			) {
				Card(modifier = Modifier
					.fillMaxHeight()
					.width(120.dp)) {
					Image(
						painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500/${movie.posterPath}"),
						contentDescription = null,
						contentScale = ContentScale.FillHeight
					)
				}
				movie.title?.let {
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
					rating = movie.voteAverage,
					modifier = Modifier.size(60.dp),
					textSize = 25.sp,
					fontWeight = FontWeight.Bold
				)
			}

		}
	}
}