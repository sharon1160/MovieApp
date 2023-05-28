package com.example.movieapp.view.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.view.ui.screens.detail.DetailScreen
import com.example.movieapp.view.ui.screens.favorites.FavoritesScreen
import com.example.movieapp.view.ui.screens.login.LoginScreen
import com.example.movieapp.view.ui.screens.logout.LogoutScreen
import com.example.movieapp.view.ui.screens.search.SearchScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Search.route) {
        composable("login") {
            LoginScreen()
        }
        composable(NavigationItem.Search.route) {
            SearchScreen()
        }
        composable(NavigationItem.Favorites.route) {
            FavoritesScreen()
        }
        composable("detail") {
            DetailScreen()
        }
        composable("logout") {
            LogoutScreen()
        }
    }
}
