package com.example.k_fit.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.k_fit.ui.theme.md_theme_light_secondary

@Composable
fun CustomRedirectionButton(
    onClick: () -> Unit, imageVector: ImageVector, description: String
) {
    IconButton(
        modifier = Modifier
            .clip(CircleShape)
            .background(color = md_theme_light_secondary),
        onClick = onClick,
    ) {
        Icon(imageVector = imageVector, description)
    }
}

@Preview
@Composable
fun PreviewCustomRedirectionButton() {
    CustomRedirectionButton({}, Icons.Filled.ArrowBack, "")
}