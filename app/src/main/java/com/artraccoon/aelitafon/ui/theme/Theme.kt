package com.artraccoon.aelitafon.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val AelitaDarkColorScheme: ColorScheme = darkColorScheme(
    primary = AelitaViolet,
    secondary = MutedViolet,
    background = DeepBlack,
    surface = PanelBlack,
    onPrimary = DeepBlack,
    onBackground = SoftViolet,
    onSurface = SoftViolet,
    outline = MutedViolet,
)

@Composable
fun AelitaFonTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AelitaDarkColorScheme,
        content = content,
    )
}
