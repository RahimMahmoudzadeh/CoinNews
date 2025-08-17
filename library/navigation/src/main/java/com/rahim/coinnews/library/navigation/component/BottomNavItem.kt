package com.rahim.coinnews.library.navigation.component

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.rahim.coinnews.library.designsystem.R
import com.rahim.coinnews.library.navigation.config.ConfigChildComponent

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    @StringRes val title: Int
) {
    data object Home : BottomNavItem(
        ConfigChildComponent.Home.toString(),
        Icons.Default.Home,
        R.string.home
    )
    data object Favorite : BottomNavItem(
        ConfigChildComponent.Favorites.toString(),
        Icons.Default.Favorite,
        R.string.favorite
    )
}
