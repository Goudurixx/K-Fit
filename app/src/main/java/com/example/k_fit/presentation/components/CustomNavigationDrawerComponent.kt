package com.example.k_fit.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoiseControlOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.k_fit.R
import com.example.k_fit.ui.theme.secondaryContainer

@Composable
fun CustomNavigationDrawerComponent(
    modifier: Modifier, selectedIndex: Int,
    onNavigationItemSelected: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        NavDrawerHeader()
        Divider()
        NavDrawerItems(modifier, selectedIndex, onNavigationItemSelected)
        Spacer(modifier = modifier.fillMaxHeight(0.9f))
        Divider()
        NavDrawerFooter()
    }
}

@Composable
fun NavDrawerHeader() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.app_name),
        )
    }
}


@Composable
fun NavDrawerItems(
    modifier: Modifier,
    selectedIndex: Int,
    onNavigationItemSelected: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        NavDrawerItem(
            modifier = modifier,
            label = "Main",
            onClick = { onNavigationItemSelected(0) },
            drawerIndex = 0,
            selectedIndex = selectedIndex,
            icon = Icons.Filled.NoiseControlOff
        )
        NavDrawerItem(
            modifier = modifier,
            label = "Favorite",
            onClick = { onNavigationItemSelected(1) },
            drawerIndex = 1,
            selectedIndex = selectedIndex,
            icon = Icons.Filled.NoiseControlOff
        )
    }
}

@Composable
fun NavDrawerFooter() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Kfit - 2023")
    }
}


@Composable
fun NavDrawerItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    drawerIndex: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                shape = RoundedCornerShape(60.dp), color = if (selectedIndex == drawerIndex) {
                  MaterialTheme.colors.secondaryContainer
                } else {
                   Color.Transparent
                }
            )
            .clickable(
                enabled = true,
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.subtitle1,
        )
    }
}

@Preview("preview of the NavigatonDrawerComponent", showSystemUi = true)
@Composable
fun PreviewCustomNavigationDrawerComponent() {

    var selectedIndex by remember { mutableStateOf(0) }
    CustomNavigationDrawerComponent(modifier = Modifier,
        selectedIndex = selectedIndex,
        onNavigationItemSelected = { index ->
            selectedIndex = index
        })
}