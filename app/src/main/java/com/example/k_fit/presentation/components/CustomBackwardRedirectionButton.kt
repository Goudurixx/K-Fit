package com.example.k_fit.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.k_fit.ui.theme.RedirectionButtonColor

@Composable
fun CustomBackwardRedirectionButton(
    onClick: () -> Unit,
) {
    IconButton(
        modifier = Modifier
            .clip(CircleShape)
            .background(color = RedirectionButtonColor),
        onClick = onClick,
    ) {
        Icon(imageVector = Icons.Filled.ArrowBack, "Go Forward")
    }
}