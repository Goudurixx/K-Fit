package com.example.k_fit

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.k_fit.presentation.features.login.LoginScreen
import com.example.k_fit.presentation.features.mainPage.MainPageScreen
import com.example.k_fit.presentation.features.register.RegisterScreen
import com.example.k_fit.presentation.features.userProfile.UserProfileScreen

@Composable
fun Router() {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = ScreenRoute.Login.route
    ) {
        composable(ScreenRoute.Register.route) {
            RegisterScreen(navHostController)
        }
        composable(ScreenRoute.Login.route) {
            LoginScreen(navHostController)
        }
        composable(ScreenRoute.MainPage.route) {
            MainPageScreen(navHostController)
        }
        composable(ScreenRoute.UserProfile.route) {
          UserProfileScreen(navHostController)
        }
    }
}

sealed class ScreenRoute(val route: String) {
    object Login : ScreenRoute("login")
    object Register : ScreenRoute("register")
    object MainPage : ScreenRoute("mainPage")
    object UserProfile : ScreenRoute("userProfile")
}