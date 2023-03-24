package com.example.k_fit.presentation.components.cardComponent

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun CardSubTitle(cardSubTitle: String) {
    Text(
        text = cardSubTitle,
        style = MaterialTheme.typography.body2,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
    )
}