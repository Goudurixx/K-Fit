package com.example.k_fit.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    error = md_theme_light_error,
    onError = md_theme_light_onError,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
)


private val DarkColorPalette = darkColors(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    error = md_theme_dark_error,
    onError = md_theme_dark_onError,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
)

val Colors.primaryContainer: Color
    get() = if (isLight) md_theme_light_primaryContainer else md_theme_dark_primaryContainer

val Colors.onPrimaryContainer: Color
    get() = if (isLight) md_theme_light_onPrimaryContainer else md_theme_dark_onPrimaryContainer

val Colors.secondaryContainer: Color
    get() = if (isLight) md_theme_light_secondaryContainer else md_theme_dark_secondaryContainer

val Colors.onSecondaryContainer: Color
    get() = if (isLight) md_theme_light_onSecondaryContainer else md_theme_dark_onSecondaryContainer

val Colors.tertiary: Color
    get() = if (isLight) md_theme_light_tertiary else md_theme_dark_tertiary

val Colors.onTertiary: Color
    get() = if (isLight) md_theme_light_onTertiary else md_theme_dark_onTertiary

val Colors.tertiaryContainer: Color
    get() = if (isLight) md_theme_light_tertiaryContainer else md_theme_dark_tertiaryContainer

val Colors.onTertiaryContainer: Color
    get() = if (isLight) md_theme_light_onTertiaryContainer else md_theme_dark_onTertiaryContainer

val Colors.errorContainer: Color
    get() = if (isLight) md_theme_light_errorContainer else md_theme_dark_errorContainer

val Colors.onErrorContainer: Color
    get() = if (isLight) md_theme_light_onErrorContainer else md_theme_dark_onErrorContainer

val Colors.outline: Color
    get() = if (isLight) md_theme_light_outline else md_theme_dark_outline

val Colors.surfaceVariant: Color
    get() = if (isLight) md_theme_light_surfaceVariant else md_theme_dark_surfaceVariant

val Colors.onSurfaceVariant: Color
    get() = if (isLight) md_theme_light_onSurfaceVariant else md_theme_dark_onSurfaceVariant

val Colors.inverseSurface: Color
    get() = if (isLight) md_theme_light_inverseSurface else md_theme_dark_inverseSurface

val Colors.inverseOnSurface: Color
    get() = if (isLight) md_theme_light_inverseOnSurface else md_theme_dark_inverseOnSurface

val Colors.inversePrimary: Color
    get() = if (isLight) md_theme_light_inversePrimary else md_theme_dark_inversePrimary

val Colors.shadow: Color
    get() = if (isLight) md_theme_light_shadow else md_theme_dark_shadow

val Colors.surfaceTint: Color
    get() = if (isLight) md_theme_light_surfaceTint else md_theme_dark_surfaceTint

val Colors.outlineVariant: Color
    get() = if (isLight) md_theme_light_outlineVariant else md_theme_dark_outlineVariant

val Colors.scrim: Color
    get() = if (isLight) md_theme_light_scrim else md_theme_dark_scrim


@Composable
fun KFitTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
