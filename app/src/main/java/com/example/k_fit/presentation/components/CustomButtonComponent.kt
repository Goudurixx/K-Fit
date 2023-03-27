package com.example.k_fit.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.k_fit.ui.theme.md_theme_light_background
import com.example.k_fit.ui.theme.md_theme_light_onBackground

@Composable
fun CustomButtonComponent(
    title: String, onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = md_theme_light_background),
        shape = RoundedCornerShape(100),
        elevation = ButtonDefaults.elevation(2.dp),
        modifier = Modifier
            .height(40.dp)
            .width(95.dp),
        onClick = onClick
    ) {
        Text(text = title, color = md_theme_light_onBackground)
    }
}

@Preview("Preview CustomButtomComponent", showBackground = true)
@Composable
fun PreviewCustomButtonComponent() {
    CustomButtonComponent("Test", {})
}