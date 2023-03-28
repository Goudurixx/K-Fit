package com.example.k_fit

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.k_fit.presentation.features.login.LoginScreen
import com.example.k_fit.presentation.features.loginOrRegister.LoginOrRegisterScreen
import com.example.k_fit.presentation.features.mainPage.MainPage
import com.example.k_fit.presentation.features.register.RegisterScreen
import com.example.k_fit.presentation.features.userProfile.UserProfileScreen

@Composable
fun Router() {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = ScreenRoute.LoginOrRegister.route
    ) {
        composable(ScreenRoute.LoginOrRegister.route) {
            LoginOrRegisterScreen(navHostController)
        }
        composable(ScreenRoute.Register.route) {
            RegisterScreen(navHostController)
        }
        composable(ScreenRoute.Login.route) {
            LoginScreen(navHostController)
        }
        composable(ScreenRoute.MainPage.route) {
            MainPage(navHostController)
        }
        composable(ScreenRoute.UserProfile.route) {
            UserProfileScreen(navHostController)
        }
    }
}

sealed class ScreenRoute(val route: String) {
    object LoginOrRegister : ScreenRoute("loginOrRegister")
    object Login : ScreenRoute("login")
    object Register : ScreenRoute("register")
    object MainPage : ScreenRoute("mainPage")
    object UserProfile : ScreenRoute("userProfile")
}