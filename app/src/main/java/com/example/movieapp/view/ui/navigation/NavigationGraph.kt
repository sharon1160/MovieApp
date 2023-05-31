package com.example.movieapp.view.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.room.Room
import com.example.movieapp.service.data.database.FavoriteMovieDatabase
import com.example.movieapp.service.model.FavoritesSingleton
import com.example.movieapp.service.repository.FavoriteMovieRepository
import com.example.movieapp.view.ui.LoginApplication.Companion.prefs
import com.example.movieapp.view.ui.screens.detail.DetailScreen
import com.example.movieapp.view.ui.screens.favorites.FavoritesScreen
import com.example.movieapp.view.ui.screens.login.LoginScreen
import com.example.movieapp.view.ui.screens.search.SearchScreen
import com.example.movieapp.viewmodel.FavoriteMovieViewModel
import com.example.movieapp.viewmodel.LoginViewModel
import com.example.movieapp.viewmodel.SearchViewModel

private val favoritesSingleton = FavoritesSingleton

@Composable
fun NavigationGraph(navController: NavHostController) {
    val startRoute: String = getStartDestination()

    val localContext = LocalContext.current
    val db = Room.databaseBuilder(localContext, FavoriteMovieDatabase::class.java, "favorite_movie_db").build()
    val dao = db.dao
    val repository = FavoriteMovieRepository(dao)
    val favoriteMovieViewModel = FavoriteMovieViewModel(repository)
    NavHost(navController, startDestination = startRoute) {
        composable(NavigationItem.Search.route) {
            val searchViewModel = SearchViewModel()
            SearchScreen(searchViewModel, favoriteMovieViewModel)
        }
        composable(NavigationItem.Favorites.route) {
            FavoritesScreen(favoriteMovieViewModel)
        }
        composable("detail") {
            DetailScreen()
        }
        composable("login") {
            val loginViewModel = LoginViewModel()
            favoriteMovieViewModel.deleteAllFavoriteMovieData()
            LoginScreen(loginViewModel, navController)
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
