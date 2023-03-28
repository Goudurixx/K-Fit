package com.example.k_fit.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.k_fit.ui.theme.RainbowColorsBrush

@Composable
fun CustomCardIcon(cardIcon: ImageVector, contentDescription: String) {
    Image(
        imageVector = cardIcon,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(16.dp)
            .size(40.dp)
            .clip(CircleShape)
            .border(
                BorderStroke(1.dp, RainbowColorsBrush),
                CircleShape
            )
    )
}