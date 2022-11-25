package com.technipixl.evand.movie2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
fun Movie2App(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: BottomNavItems.SearchMovie.name


    Scaffold(bottomBar = {
        BottomNavigation(
            selected = currentScreen,
            navigationChanged = { navController.navigate(it) }
        )
    }) { innerPadding ->

        lateinit var selectedMovie: MovieResult.Movie

        NavHost(
            navController = navController,
            startDestination = BottomNavItems.SearchMovie.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = BottomNavItems.SearchMovie.name) {
                SearchMovie(onClick = { route, movie ->
                    selectedMovie = movie
                    navController.navigate(route)
                })
            }

            composable(route = BottomNavItems.PopularMovie.name) {
                PopularMovie(onClick = { route, movie ->
                    selectedMovie = movie
                    navController.navigate(route)
                })
            }

            composable(route = BottomNavItems.DetailsMovie.name) {
                DetailsMovie(onClick = { route, movie ->
                    selectedMovie = movie
                    navController.navigate(route)
                })
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