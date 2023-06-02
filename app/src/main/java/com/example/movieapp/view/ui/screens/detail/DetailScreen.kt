package com.example.movieapp.view.ui.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.service.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(scope: CoroutineScope, bsScaffoldState: BottomSheetScaffoldState, movie: Movie) {
    DetailContent(scope, bsScaffoldState, movie)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(
    scope: CoroutineScope,
    bsScaffoldState: BottomSheetScaffoldState,
    movie: Movie
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(128.dp)
            .padding(20.dp)
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
    }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                scope.launch { bsScaffoldState.bottomSheetState.hide() }
            },
        ) {
            Text("Close")
        }
    }
}
