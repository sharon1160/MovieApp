package com.example.movieapp.view.ui.screens.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.movieapp.service.model.Movie
import com.example.movieapp.view.ui.theme.MovieAppTheme
import com.example.movieapp.view.ui.theme.OpenSans
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

val movies: MutableList<Movie> = mutableListOf(
    Movie(
        poster = "https://m.media-amazon.com/images/M/MV5BZDNjOGNhN2UtNmNhMC00YjU4LWEzMmUtNzRkM2RjN2RiMjc5XkEyXkFqcGdeQXVyMTU0OTM5ODc1._V1_SX300.jpg",
        title = "Batman",
        year = "1907",
        isFavorite = false
    ),
    Movie(
        poster = "https://m.media-amazon.com/images/M/MV5BZDNjOGNhN2UtNmNhMC00YjU4LWEzMmUtNzRkM2RjN2RiMjc5XkEyXkFqcGdeQXVyMTU0OTM5ODc1._V1_SX300.jpg",
        title = "Batman",
        year = "1907",
        isFavorite = true
    ),
    Movie(
        poster = "https://m.media-amazon.com/images/M/MV5BZDNjOGNhN2UtNmNhMC00YjU4LWEzMmUtNzRkM2RjN2RiMjc5XkEyXkFqcGdeQXVyMTU0OTM5ODc1._V1_SX300.jpg",
        title = "Batman",
        year = "1907",
        isFavorite = true
    ),
    Movie(
        poster = "https://m.media-amazon.com/images/M/MV5BZDNjOGNhN2UtNmNhMC00YjU4LWEzMmUtNzRkM2RjN2RiMjc5XkEyXkFqcGdeQXVyMTU0OTM5ODc1._V1_SX300.jpg",
        title = "Batman",
        year = "1907",
        isFavorite = false
    ),
    Movie(
        poster = "https://m.media-amazon.com/images/M/MV5BZDNjOGNhN2UtNmNhMC00YjU4LWEzMmUtNzRkM2RjN2RiMjc5XkEyXkFqcGdeQXVyMTU0OTM5ODc1._V1_SX300.jpg",
        title = "Batman",
        year = "1907",
        isFavorite = false
    ),
    Movie(
        poster = "https://m.media-amazon.com/images/M/MV5BZDNjOGNhN2UtNmNhMC00YjU4LWEzMmUtNzRkM2RjN2RiMjc5XkEyXkFqcGdeQXVyMTU0OTM5ODc1._V1_SX300.jpg",
        title = "Batman",
        year = "1907",
        isFavorite = true
    ),
    Movie(
        poster = "https://m.media-amazon.com/images/M/MV5BZDNjOGNhN2UtNmNhMC00YjU4LWEzMmUtNzRkM2RjN2RiMjc5XkEyXkFqcGdeQXVyMTU0OTM5ODc1._V1_SX300.jpg",
        title = "Batman",
        year = "1907",
        isFavorite = false
    ),
    Movie(
        poster = "https://m.media-amazon.com/images/M/MV5BZDNjOGNhN2UtNmNhMC00YjU4LWEzMmUtNzRkM2RjN2RiMjc5XkEyXkFqcGdeQXVyMTU0OTM5ODc1._V1_SX300.jpg",
        title = "Batman",
        year = "1907",
        isFavorite = false
    ),
)

@Composable
fun FavoritesScreen() {
    MovieAppTheme {
        FavoritesContent()
    }
}

@Composable
fun FavoritesContent() {
    Column(
        modifier = Modifier.padding(top = 20.dp)
    ) {
        CarouselCard()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CarouselCard() {
    val pagerState = rememberPagerState(initialPage = 2)
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            count = movies.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 80.dp),
            modifier = Modifier
                .height(750.dp)
        ) {page ->
            Card(
                modifier = Modifier
                    .height(420.dp)
                    .width(250.dp)
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(start = 0.80f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                            .also {scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    },
                shape = MaterialTheme.shapes.large
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(18.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(movies[page].poster)
                            .crossfade(true)
                            .scale(Scale.FILL)
                            .build(),
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                            .height(300.dp)
                            .width(280.dp)
                            .clip(MaterialTheme.shapes.medium),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                    Text(
                        text = movies[page].title,
                        fontWeight = FontWeight.Bold,
                        fontFamily = OpenSans,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(text = movies[page].year, fontFamily = OpenSans)
                }
            }
        }
    }
}

@Preview
@Composable
fun FavoritesPreview() {
    FavoritesContent()
}