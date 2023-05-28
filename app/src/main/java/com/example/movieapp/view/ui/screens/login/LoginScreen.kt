@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.movieapp.view.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieapp.R
import com.example.movieapp.viewmodel.LoginViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding

@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LoginContent(
            navController,
            loginViewModel::validEntries,
            loginViewModel::saveSession
        )
    }
}

@Composable
fun LoginContent(
    navController: NavHostController,
    validEntries: (String, String) -> Boolean,
    saveSession: (String, String) -> Unit
) {
    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    ProvideWindowInsets {
        Column(
            modifier = Modifier
                .navigationBarsWithImePadding()
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(
                16.dp,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Profile()

            val username = TextInput(
                inputType = InputType.Name,
                keyboardActions = KeyboardActions(
                    onNext = {
                        passwordFocusRequester.requestFocus()
                    }
                )
            )

            val password = TextInput(
                inputType = InputType.Password,
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                focusRequester = passwordFocusRequester
            )

            SignInButton(navController, username, password, validEntries, saveSession)

            Line()

            SignUpContainer()
        }
    }
}

@Composable
fun SignInButton(
    navController: NavHostController,
    username: String,
    password: String,
    validEntries: (String, String) -> Boolean,
    saveSession: (String, String) -> Unit
) {
    val context = LocalContext.current
    Button(
        onClick = {
            if (validEntries(username, password)) {
                saveSession(username, password)
                navController.navigate(route = "search")
            } else {
                Toast.makeText(context,"Incorrect data, try again", Toast.LENGTH_SHORT).show()
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
    ) {
        Text(text = "SIGN IN", modifier = Modifier.padding(vertical = 10.dp))
    }
}

@Composable
fun SignUpContainer() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Don't have an account?",
            color = MaterialTheme.colorScheme.onBackground
        )
        SignUpButton()
    }
}

@Composable
fun Line() {
    Divider(
        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
        thickness = 1.dp,
        modifier = Modifier.padding(top = 48.dp)
    )
}

@Composable
fun SignUpButton() {
    TextButton(onClick = { }) {
        Text(text = "SIGN UP")
    }
}

@Composable
fun Profile() {
    Icon(
        painter = painterResource(id = R.drawable.account_icon),
        contentDescription = null,
        modifier = Modifier.size(200.dp),
        tint = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun TextInput(
    inputType: InputType,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions
): String {
    var value by remember { mutableStateOf("") }

    TextField(
        value = value,
        onValueChange = { value = it },
        modifier = Modifier
            .fillMaxWidth()
            .focusOrder(focusRequester ?: FocusRequester()),
        leadingIcon = { Icon(imageVector = inputType.icon, null) },
        label = { Text(text = inputType.label) },
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        keyboardOptions = inputType.keyboardOptions,
        visualTransformation = inputType.visualTransformation,
        keyboardActions = keyboardActions
    )
    return value
}
