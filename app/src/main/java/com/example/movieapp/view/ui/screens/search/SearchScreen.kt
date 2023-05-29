package com.example.movieapp.view.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.view.ui.theme.MovieAppTheme

@Composable
fun SearchScreen() {
    MovieAppTheme {
        SearchContent()
    }
}

@Composable
fun SearchContent() {
    Box(
        modifier = Modifier
            .padding(top= 70.dp)
            .fillMaxSize()
    ) {
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

@Preview
@Composable
fun SearchView() {
    MovieAppTheme {
        SearchContent()
    }
}
