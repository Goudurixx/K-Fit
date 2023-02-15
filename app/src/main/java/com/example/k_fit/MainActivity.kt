package com.example.k_fit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.k_fit.presentation.features.login.LoginScreen
import com.example.k_fit.presentation.features.loginOrRegister.LoginOrRegisterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Router()
        }
    }
}

@Composable
fun Router(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "login"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("loginOrRegister") {
            LoginOrRegisterScreen(
                onNavigateToFriends = {
                    navController.navigate("login") {
                    }
                },
            )
        }
        composable("login") {
            LoginScreen(
                onNavigateToFriends = {
                },
            )
        }
    }
}

