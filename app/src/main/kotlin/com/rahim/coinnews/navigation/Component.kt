package com.rahim.coinnews.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rahim.coinnews.library.navigation.component.BottomNavItem
import com.rahim.coinnews.library.navigation.config.ConfigChildComponent

@Composable
fun BottomNavigationBar(
    component: RootComponent,
    configuration: Any,
    modifier: Modifier = Modifier
) {

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.onBackground,
        modifier = modifier.shadow(1.dp)
    ) {
        NavigationBarItem(
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.secondary,
                unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                selectedTextColor = MaterialTheme.colorScheme.tertiary,
                indicatorColor = MaterialTheme.colorScheme.onPrimary
            ),
            onClick = {
                component.onTabClick(ConfigChildComponent.Home)
            },
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(BottomNavItem.Home.iconId),
                    contentDescription = BottomNavItem.Home.route,
                    modifier = Modifier.size(22.dp)
                )
            },
            label = {
                if (configuration is ConfigChildComponent.Home)
                    Text(text = stringResource(BottomNavItem.Home.title), fontSize = 12.sp)
            },
            selected = configuration is ConfigChildComponent.Home,
        )
        NavigationBarItem(
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.secondary,
                unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                selectedTextColor = MaterialTheme.colorScheme.tertiary,
                indicatorColor = MaterialTheme.colorScheme.onPrimary
            ),
            onClick = {
                component.onTabClick(ConfigChildComponent.Favorites)
            },
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(BottomNavItem.Favorite.iconId),
                    contentDescription = BottomNavItem.Favorite.route,
                    modifier = Modifier.size(22.dp)
                )
            },
            label = {
                if (configuration is ConfigChildComponent.Favorites)
                    Text(text = stringResource(BottomNavItem.Favorite.title), fontSize = 12.sp)
            },
            selected = configuration is ConfigChildComponent.Favorites,
        )
    }
}