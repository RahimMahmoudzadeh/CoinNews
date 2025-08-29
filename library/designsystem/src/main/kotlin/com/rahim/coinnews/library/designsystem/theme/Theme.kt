package com.rahim.coinnews.library.designsystem.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = White,
    onPrimary = DarkBronze,
    secondary = Flavescent,
    onSecondary = Gray,
    tertiary = VegasGold,
    onBackground = PullmanGreen
)

private val LightColorScheme = lightColorScheme(
    primary = Dark,
    onPrimary = DarkBronze,
    onSecondary = Gray,
    secondary = Flavescent,
    tertiary = VegasGold,
    onBackground = PullmanGreen
)

@Composable
fun CoinNewsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}