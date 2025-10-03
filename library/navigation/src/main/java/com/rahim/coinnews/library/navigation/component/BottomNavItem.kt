package com.rahim.coinnews.library.navigation.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.rahim.coinnews.library.designsystem.R
import com.rahim.coinnews.library.navigation.config.ConfigChildComponent

sealed class BottomNavItem(
    val route: String,
    @DrawableRes
    val iconId: Int,
    @StringRes
    val title: Int
) {
    data object Home : BottomNavItem(
        route = ConfigChildComponent.Home.toString(),
        iconId = R.drawable.ic_home,
        title = R.string.home
    )

    data object Favorite : BottomNavItem(
        route = ConfigChildComponent.Favorites.toString(),
        iconId = R.drawable.ic_favorite,
        title = R.string.favorite
    )
}
