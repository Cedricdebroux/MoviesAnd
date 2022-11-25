package com.technipixl.evand.movie2

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter


@Composable
fun BottomNavigation(
	modifier: Modifier = Modifier,
	selected: String,
	navigationChanged: (String) -> Unit
){
	androidx.compose.material.BottomNavigation(modifier) {
		BottomNavigationItem(
			icon = {
				Icon(
					imageVector = Icons.Default.Search,
					contentDescription = null
				)
			},
			label = {
				Text("Search")
			},
			selected = selected == BottomNavItems.SearchMovie.name,
			onClick = { navigationChanged(BottomNavItems.SearchMovie.name) }
		)
		BottomNavigationItem(
			icon = {
				Icon(
					imageVector = Icons.Default.Star,
					contentDescription = null
				)
			},
			label = {
				Text("Movies")
			},
			selected = selected == BottomNavItems.PopularMovie.name,
			onClick = { navigationChanged(BottomNavItems.PopularMovie.name) }
		)
	}
}