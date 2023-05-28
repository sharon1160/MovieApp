package com.example.movieapp.view.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.view.ui.LoginApplication.Companion.prefs
import com.example.movieapp.view.ui.screens.detail.DetailScreen
import com.example.movieapp.view.ui.screens.favorites.FavoritesScreen
import com.example.movieapp.view.ui.screens.login.LoginScreen
import com.example.movieapp.view.ui.screens.logout.LogoutScreen
import com.example.movieapp.view.ui.screens.search.SearchScreen
import com.example.movieapp.viewmodel.LoginViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    val startRoute: String = getStartDestination()
    NavHost(navController, startDestination = startRoute) {
        composable("login") {
            val loginViewModel = LoginViewModel()
            LoginScreen(loginViewModel, navController)
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

fun getStartDestination(): String {
    return if (prefs.isLoggedIn()){
        "search"
    } else {
        "login"
    }
}
