package com.example.movieapp.view.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movieapp.R
import com.example.movieapp.view.ui.theme.OpenSans

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(navController: NavController, title: String, onLogoutClick: () -> Unit) {
    val items = listOf(
        NavigationItem.Search,
        NavigationItem.Favorites
    )

    val showBottomBar = navController
        .currentBackStackEntryAsState().value?.destination?.route in items.map { it.route }

    if (showBottomBar) {
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
                LogOutIconButton(
                    painter = painterResource(id = R.drawable.logout_icon),
                    description = "Logout Icon",
                    tint = MaterialTheme.colorScheme.onPrimary
                ) {
                    onLogoutClick()
                }
            }
        )
    }
}

@Composable
fun LogOutIconButton(painter: Painter, description: String, tint: Color, onClick: () -> Unit) {
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