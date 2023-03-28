package com.example.k_fit

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.k_fit.presentation.features.login.LoginScreen
import com.example.k_fit.presentation.features.loginOrRegister.LoginOrRegisterScreen
import com.example.k_fit.presentation.features.mainPage.MainPage
import com.example.k_fit.presentation.features.register.RegisterScreen
import com.example.k_fit.presentation.features.userProfile.UserProfileScreen

@Composable
fun Router(navigationHostController: NavHostController) {
    NavHost(
        navController = navigationHostController,
        startDestination = ScreenRoute.LoginOrRegister.route
    ) {
        composable(ScreenRoute.Login.route) {
            LoginOrRegisterScreen(onNavigateToLogin = { /*TODO*/ }) {}
        }
        composable(ScreenRoute.Register.route) {
            RegisterScreen(redirection = { /*TODO*/ })
        }
        composable(ScreenRoute.Login.route) {
            LoginScreen(redirection = { /*TODO*/ })
        }
        composable(ScreenRoute.MainPage.route) {
            MainPage(userProfile = { /*TODO*/ })
        }
        composable(ScreenRoute.UserProfile.route) {
            UserProfileScreen(goBack = { /*TODO*/ })
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