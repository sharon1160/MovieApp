package com.example.movieapp.view.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import com.example.movieapp.R
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.view.ui.theme.MovieAppTheme
import com.example.movieapp.view.ui.theme.OpenSans

@Composable
fun SearchScreen() {
    MovieAppTheme {
        Scaffold(
            topBar = { Toolbar(title = "Search", onSettingsClick = {}) },
            content = {
                Content(it)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(title: String, onSettingsClick: () -> Unit) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            LogOutButton(
                painter = painterResource(id = R.drawable.logout_icon),
                description = "Logout Icon",
                tint = MaterialTheme.colorScheme.onPrimary
            ) {
                onSettingsClick()
            }
        }
    )
}

@Composable
fun LogOutButton(painter: Painter, description: String, tint: Color, onClick: () -> Unit) {
    IconButton(onClick = {
        onClick()
    }) {
        Icon(
            painter = painter,
            contentDescription = description,
            tint = tint,
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
        )
    }
}

@Composable
fun Content(paddingValues: PaddingValues) {
    Box(modifier = Modifier
        .padding(paddingValues)
        .fillMaxSize()) {
        SearchMovieBar()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchMovieBar() {
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
            searchHistoryItems.forEach{
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

@Preview
@Composable
fun SearchView() {
    MovieAppTheme {
        Scaffold(
            topBar = { Toolbar(title = "Search", onSettingsClick = {}) },
            content = {
                Content(it)
            }
        )
    }
}
