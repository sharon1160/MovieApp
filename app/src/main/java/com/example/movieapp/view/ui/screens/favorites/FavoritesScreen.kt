package com.example.movieapp.view.ui.screens.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp.view.ui.theme.MovieAppTheme

@Composable
fun FavoritesScreen() {
    MovieAppTheme {
        FavoritesContent()
    }
}

@Composable
fun FavoritesContent() {
    Box(
        modifier = Modifier
            .padding(top= 70.dp)
            .fillMaxSize()
    ) {
        Text(text = "Helo 3")
    }
}