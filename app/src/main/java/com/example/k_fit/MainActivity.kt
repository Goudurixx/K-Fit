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
import com.example.k_fit.presentation.features.mainPage.MainPage
import com.example.k_fit.presentation.features.register.RegisterScreen
import com.example.k_fit.presentation.features.userProfile.UserProfileScreen
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            Router()
        }
    }
}

@Composable
fun Router(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "loginOrRegister"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("loginOrRegister") {
            LoginOrRegisterScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("register") {
            RegisterScreen(navController)
        }
        composable("mainPage") {
            MainPage(navController)
        }
        composable("userProfile") {
            UserProfileScreen(navController)
        }
    }
}