package com.example.movieapp.view.ui.navigation

import com.example.movieapp.R

sealed class NavigationItem(var title: String, var icon: Int, var route: String) {
    object Search : NavigationItem("Search", R.drawable.search_icon, "search")
    object Favorites : NavigationItem("Favorites", R.drawable.favorite_fill_icon, "favorites")
}
