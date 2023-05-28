package com.example.movieapp.view.ui.screens.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.view.ui.navigation.NavigationBar
import com.example.movieapp.view.ui.navigation.NavigationGraph
import com.example.movieapp.view.ui.theme.MovieAppTheme

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    MovieAppTheme {
        Scaffold(
            bottomBar = { NavigationBar(navController = navController) }
        ) {
            NavigationGraph(navController = navController)
        }
    }
}
