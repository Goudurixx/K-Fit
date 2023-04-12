package com.example.k_fit

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.k_fit.data.storage.UserStorage
import com.example.k_fit.presentation.features.login.LoginScreen
import com.example.k_fit.presentation.features.mainPage.MainPageScreen
import com.example.k_fit.presentation.features.register.RegisterScreen
import com.example.k_fit.presentation.features.splashScreen.SplashScreen
import com.example.k_fit.presentation.features.userProfile.UserProfileScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

@Composable
fun Router(context: Context) {
    val navHostController = rememberNavController()
    val userStorage = UserStorage(context)
    val userFlow = userStorage.getUser()
    var startDestination = ScreenRoute.SplashScreen.route

    LaunchedEffect(userFlow) {
        userFlow.collect { user ->
            startDestination = if (user.email.isNotBlank()) {
                ScreenRoute.MainPage.route
            } else {
                ScreenRoute.Login.route
            }
            Thread.sleep(1500)
            navHostController.navigate(startDestination) {
                popUpTo(navHostController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }

    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(ScreenRoute.SplashScreen.route) {
            SplashScreen()
        }
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
    object SplashScreen : ScreenRoute("splashScreen")
    object Login : ScreenRoute("login")
    object Register : ScreenRoute("register")
    object MainPage : ScreenRoute("mainPage")
    object UserProfile : ScreenRoute("userProfile")
}