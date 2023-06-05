package com.example.movieapp.view.ui.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.service.model.Movie

@Composable
fun DetailScreen(
    movie: Movie,
    plot: String,
    director: String
) {
    DetailContent(movie, plot, director)
}

@Composable
fun DetailContent(
    movie: Movie,
    plot: String,
    director: String
) {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(5.dp)
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
                    text = "${movie.year} | $director",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = plot,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
