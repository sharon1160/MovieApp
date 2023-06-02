package com.example.movieapp.view.ui.screens.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.movieapp.service.model.Movie
import com.example.movieapp.view.ui.screens.detail.DetailScreen
import com.example.movieapp.view.ui.theme.MovieAppTheme
import com.example.movieapp.view.ui.theme.OpenSans
import com.example.movieapp.viewmodel.FavoriteMovieViewModel
import com.google.accompanist.pager.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(favoriteMovieViewModel: FavoriteMovieViewModel) {
    val uiState by favoriteMovieViewModel.uiState.collectAsState()

    MovieAppTheme {
        val scope = rememberCoroutineScope()
        val bsScaffoldState = rememberBottomSheetScaffoldState(
            SheetState(skipHiddenState = false, skipPartiallyExpanded = true)
        )
        val onClickItem = {
            scope.launch { bsScaffoldState.bottomSheetState.expand() }
        }

        BottomSheetScaffold(
            scaffoldState = bsScaffoldState,
            sheetPeekHeight = 178.dp,
            sheetShape = MaterialTheme.shapes.large,
            sheetContent = { DetailScreen(scope, bsScaffoldState, uiState.movieDetail) }
        ) {
            FavoritesContent(
                favoriteMovieViewModel::getAllFavoriteMovieData,
                onClickItem,
                favoriteMovieViewModel::updateMovieDetail
            )
        }
    }
}

@Composable
fun FavoritesContent(
    getFavorites: () -> List<Movie>,
    onClickItem: () -> Job,
    updateMovieDetail: (Movie) -> Unit
) {
    Column(
        modifier = Modifier.padding(top = 20.dp)
    ) {
        CarouselCard(getFavorites, onClickItem, updateMovieDetail)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CarouselCard(
    getFavorites: () -> List<Movie>,
    onClickItem: () -> Job,
    updateMovieDetail: (Movie) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 2)
    val moviesList = getFavorites()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            count = moviesList.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 80.dp),
            modifier = Modifier
                .height(750.dp)
        ) { page ->
            Card(
                modifier = Modifier
                    .height(420.dp)
                    .width(250.dp)
                    .clickable {
                        updateMovieDetail(moviesList[page])
                        onClickItem()
                    }
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = 0.80f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                            .also { scale ->
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
                            .data(moviesList[page].poster)
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
                        text = moviesList[page].title,
                        fontWeight = FontWeight.Bold,
                        fontFamily = OpenSans,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(text = moviesList[page].year, fontFamily = OpenSans)
                }
            }
        }
    }
}
