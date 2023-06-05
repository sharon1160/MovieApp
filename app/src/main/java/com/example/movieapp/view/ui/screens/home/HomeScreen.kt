package com.example.movieapp.view.ui.screens.home

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.view.ui.navigation.NavigationBar
import com.example.movieapp.view.ui.navigation.NavigationGraph
import com.example.movieapp.view.ui.navigation.ToolBar
import com.example.movieapp.view.ui.screens.logout.LogoutScreen
import com.example.movieapp.view.ui.theme.MovieAppTheme
import com.example.movieapp.viewmodel.LogoutViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val logoutViewModel = LogoutViewModel()

    MovieAppTheme {
        ModalNavigationDrawer(
            drawerContent = {
                LogoutScreen(
                    logoutViewModel = logoutViewModel,
                    navController = navController,
                    drawerState = drawerState
                )
            },
            drawerState = drawerState
        ) {
            Scaffold(
                topBar = {
                    ToolBar(
                        navController = navController,
                        title = "Search",
                        onLogoutClick = {
                            if (drawerState.isClosed) {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            } else {
                                coroutineScope.launch {
                                    drawerState.close()
                                }
                            }
                        }
                    )
                },
                bottomBar = { NavigationBar(navController = navController) }
            ) {
                NavigationGraph(navController = navController)
            }
        }
    }
}
