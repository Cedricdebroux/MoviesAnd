package com.technipixl.evand.movie2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.technipixl.evand.movie2.model.MovieResult
import com.technipixl.evand.movie2.ui.theme.Movie2Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Movie2Theme {
                Movie2App()
            }
        }
    }
}

@Composable
fun Header(currentScreen: NavRoutes, currentSearch: (String) -> Unit, backClick: () -> Unit){
    when(currentScreen){
       NavRoutes.SearchMovie -> SearchHeader { currentSearch(it) }
        NavRoutes.PopularMovie -> PopularHeader()
        NavRoutes.DetailsMovie -> DetailHeader(backClick)
    }
}

@Composable
fun Movie2App(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: NavRoutes.SearchMovie.name
    var screen by remember {
        mutableStateOf(NavRoutes.SearchMovie)
    }
    var currentSearch: String? by remember { mutableStateOf(null) }

    Scaffold(
        topBar = {
            Header(screen, {
                currentSearch = it
            }) {
                navController.popBackStack()
            }
        },
        bottomBar = {
        BottomNavigation(
            selected = currentScreen,
            navigationChanged = { navController.navigate(it) }
        )
    }) { innerPadding ->

        var selectedMovie by remember { mutableStateOf<MovieResult.Movie?>(null) }

        NavHost(
            navController = navController,
            startDestination = NavRoutes.SearchMovie.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = NavRoutes.SearchMovie.name) {
                SearchMovie(onClick = { movie ->
                    selectedMovie = movie
                    navController.navigate(NavRoutes.DetailsMovie.name) {
                        popUpTo(NavRoutes.DetailsMovie.name) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                    currentSearch = currentSearch
                )
                screen = NavRoutes.SearchMovie
            }

            composable(route = NavRoutes.PopularMovie.name) {

                PopularMovie(onClick = { movie ->
                    selectedMovie = movie
                    navController.navigate(NavRoutes.DetailsMovie.name) {
                        popUpTo(NavRoutes.DetailsMovie.name) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
                screen = NavRoutes.PopularMovie
            }

            composable(route = NavRoutes.DetailsMovie.name) {
                if (selectedMovie == null) return@composable
                DetailsMovie(onClick = {  movie ->
                    selectedMovie = movie
                    navController.navigate(NavRoutes.DetailsMovie.name) {
                        popUpTo(NavRoutes.DetailsMovie.name) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                    movie = selectedMovie!!
                )
                screen = NavRoutes.DetailsMovie
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Movie2Theme {
        Movie2App()
    }
}