package com.example.movieapp.view.ui.screens.logout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import com.example.movieapp.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieapp.view.ui.theme.OpenSans
import com.example.movieapp.viewmodel.LogoutViewModel
import kotlinx.coroutines.launch

@Composable
fun LogoutScreen(
    logoutViewModel: LogoutViewModel,
    navController: NavHostController,
    drawerState: DrawerState
) {
    ModalDrawerSheet {
        LogoutContent(
            navController,
            logoutViewModel::getUsername,
            logoutViewModel::clearSession,
            drawerState
        )
    }
}

@Composable
fun LogoutContent(
    navController: NavHostController,
    getUsername: () -> String,
    clearSession: () -> Unit,
    drawerState: DrawerState
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(
                top = 70.dp,
                bottom = 150.dp,
                start = 40.dp,
                end = 40.dp
            )
    ) {
        LogoutHeader()
        Spacer(modifier = Modifier.height(40.dp))
        ProfileImage()
        Spacer(modifier = Modifier.height(40.dp))
        DataText(label = "Username", data = getUsername())
        Spacer(modifier = Modifier.height(10.dp))
        DataText(label = "Date", data = "Last login date")
        Spacer(modifier = Modifier.height(80.dp))
        LogoutButton(navController, clearSession, drawerState)
    }
}

@Composable
fun LogoutHeader() {
    Text(
        text = "MovieApp",
        fontFamily = OpenSans,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
fun ProfileImage() {
    Box(
        modifier = Modifier.padding(20.dp)
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(id = R.drawable.account_icon),
            contentDescription = "Profile icon"
        )
    }
}

@Composable
fun DataText(label: String, data: String) {
    Row(
        modifier = Modifier.width(200.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label:",
            fontFamily = OpenSans,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = data,
            fontFamily = OpenSans,
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun LogoutButton(
    navController: NavHostController,
    clearSession: () -> Unit,
    drawerState: DrawerState
) {
    val scope = rememberCoroutineScope()
    Button(onClick = {
        clearSession()
        scope.launch{
            drawerState.close()
        }
        navController.navigate(route = "Login")
    }) {
        Text(
            text = "Logout",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            fontFamily = OpenSans
        )
    }
}
