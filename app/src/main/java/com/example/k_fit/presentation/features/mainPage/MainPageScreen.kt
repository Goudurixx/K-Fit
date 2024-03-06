package com.example.k_fit.presentation.features.mainPage

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.k_fit.R
import com.example.k_fit.ScreenRoute
import com.example.k_fit.data.storage.UserStorage
import com.example.k_fit.presentation.components.CustomNavigationDrawerComponent
import com.example.k_fit.ui.theme.scrim
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainPageScreen(
    navHostController: NavHostController
) {
    val modifier: Modifier = Modifier
    val focusManager = LocalFocusManager.current
    var selectedIndex by remember { mutableStateOf(0) }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var title by remember { mutableStateOf("") }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        scaffoldState = scaffoldState,
        topBar =
        {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Navigation icon"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navHostController.navigate(ScreenRoute.UserProfile.route) }) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Navigation icon"

                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 0.dp
            )
        },
        drawerContent = {
            CustomNavigationDrawerComponent(
                modifier = modifier,
                onChoiceClick = {
                    scope.launch {
                        scaffoldState.drawerState.apply {
                            delay(300)
                            if (isClosed) open() else close()
                        }
                    }
                },
                selectedIndex = selectedIndex,
                onNavigationItemSelected = { index ->
                    selectedIndex = index
                }
            )
        },
        drawerScrimColor = MaterialTheme.colors.scrim,
        drawerShape = RoundedCornerShape(topEnd = 32.dp, bottomEnd = 32.dp),
        content = { paddingValues ->
            when (selectedIndex) {
                0 -> {
                    WorkoutScreen(paddingValues, modifier)
                    title = stringResource(id = R.string.workout_screen_title)
                }
                1 -> {
                    FavoriteScreen(paddingValues, modifier)
                    title = stringResource(id = R.string.favorite_screen_title)
                }
                else -> {
                    Text("Screen in construction...")
                }
            }
        }
    )
}

@Preview("Preview of the main Page of the application", showSystemUi = true)
@Composable
fun PreviewMainPage() {
    val navHostController = rememberNavController()
    MainPageScreen(navHostController)
}