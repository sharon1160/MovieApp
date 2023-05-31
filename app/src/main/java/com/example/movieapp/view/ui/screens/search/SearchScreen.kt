package com.example.movieapp.view.ui.screens.search

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.service.model.FavoritesSingleton
import com.example.movieapp.service.model.Movie
import com.example.movieapp.view.ui.theme.MovieAppTheme
import com.example.movieapp.viewmodel.FavoriteMovieViewModel
import com.example.movieapp.viewmodel.SearchViewModel

private val favoritesSingleton = FavoritesSingleton

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel,
    favoriteMovieViewModel: FavoriteMovieViewModel
) {
    val uiState by searchViewModel.uiState.collectAsState()

    MovieAppTheme {
        SearchContent(
            searchViewModel::searchByTitle,
            searchViewModel::updateIsFavorite,
            favoriteMovieViewModel::insert,
            favoriteMovieViewModel::deleteMovie,
            uiState.moviesList
        )
    }
}

@Composable
fun SearchContent(
    searchByTitle: (String) -> Unit,
    updateIsFavorite: (Movie) -> Unit,
    insertFavoriteMovie: (Movie) -> Unit,
    deleteFavoriteMovie: (Movie) -> Unit,
    moviesList: MutableList<Movie>
) {
    Box(
        modifier = Modifier
            .padding(top = 60.dp)
            .fillMaxSize()
    ) {
        SearchMovieBar(searchByTitle)
        if (moviesList.isNotEmpty()) {
            MoviesList(moviesList, updateIsFavorite, insertFavoriteMovie, deleteFavoriteMovie)
        } else {
            Message()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchMovieBar(searchByTitle: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val searchHistoryItems = remember {
        mutableStateListOf<String>()
    }
    Scaffold {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = text,
            onQueryChange = {
                text = it
            },
            onSearch = {
                if (it.isNotEmpty()) {
                    searchByTitle(it)
                    searchHistoryItems.add(index = 0, element = it)
                    active = false
                    text = ""
                }
            },
            active = active,
            onActiveChange = {
                active = it
            },
            placeholder = {
                Text(text = "Search")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search icon"
                )
            },
            trailingIcon = {
                if (active) {
                    Icon(
                        modifier = Modifier.clickable {
                            if (text.isNotEmpty()) {
                                text = ""
                            } else {
                                active = false
                            }
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close icon"
                    )
                }
            }
        ) {
            searchHistoryItems.forEach {
                Row(modifier = Modifier.padding(all = 14.dp)) {
                    Icon(
                        modifier = Modifier.padding(end = 10.dp),
                        imageVector = Icons.Default.History,
                        contentDescription = "History icon"
                    )
                    Text(text = it)
                }
            }
        }
    }
}

@Composable
fun MoviesList(
    moviesList: List<Movie> = emptyList(),
    updateIsFavorite: (Movie) -> Unit,
    insertFavoriteMovie: (Movie) -> Unit,
    deleteFavoriteMovie: (Movie) -> Unit
) {
    Box(modifier = Modifier.padding(top = 70.dp, bottom = 50.dp)) {
        LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
            items(items = moviesList) { movie ->
                ListItem(movie, updateIsFavorite, insertFavoriteMovie, deleteFavoriteMovie)
            }
        }
    }
}

@Composable
fun ListItem(
    movie: Movie,
    updateIsFavorite: (Movie) -> Unit,
    insertFavoriteMovie: (Movie) -> Unit,
    deleteFavoriteMovie: (Movie) -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier.padding(vertical = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val lineColor = MaterialTheme.colorScheme.inversePrimary
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .drawBehind {
                        drawLine(
                            color = lineColor,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 2.dp.toPx()
                        )
                    }
            ) {

                var moviePoster: String = movie.poster
                if (moviePoster == "N/A") {
                    moviePoster =
                        "https://plantillasdememes.com/img/plantillas/imagen-no-disponible01601774755.jpg"
                }

                AsyncImage(
                    model = moviePoster,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 6.dp, bottom = 6.dp)
                        .height(80.dp)
                        .width(80.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )


                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = movie.year,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Light
                    )
                }
                FavoritesButton(movie, updateIsFavorite, insertFavoriteMovie, deleteFavoriteMovie)
            }
        }
    }
}

@Composable
fun FavoritesButton(
    movie: Movie,
    updateIsFavorite: (Movie) -> Unit,
    insertFavoriteMovie: (Movie) -> Unit,
    deleteFavoriteMovie: (Movie) -> Unit
) {
    IconButton(
        modifier = Modifier.padding(end = 16.dp),
        onClick = {
            if(!movie.isFavorite){
                insertFavoriteMovie(movie)
                favoritesSingleton.addMovie(movie)
            } else {
                deleteFavoriteMovie(movie)
                favoritesSingleton.removeMovie(movie)
            }
            updateIsFavorite(movie)
        }
    ) {
        val icon = if (movie.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder
        val tint = if (movie.isFavorite) Color.Red else Color.Gray
        Icon(
            imageVector = icon,
            contentDescription = "Favorite",
            tint = tint
        )
    }
}

@Composable
fun Message() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome! Do a search")
    }
}

