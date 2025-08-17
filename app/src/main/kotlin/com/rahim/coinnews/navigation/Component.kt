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
import androidx.compose.ui.res.stringResource
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
                selectedIconColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
                selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                unselectedTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                indicatorColor = MaterialTheme.colorScheme.onBackground
            ),
            onClick = {
                component.onTabClick(ConfigChildComponent.Home)
            },
            icon = {
                Icon(
                    imageVector = BottomNavItem.Home.icon,
                    contentDescription = BottomNavItem.Favorite.route,
                    modifier = Modifier.size(22.dp)
                )
            },
            label = {
                Text(text = stringResource(BottomNavItem.Favorite.title), fontSize = 12.sp)
            },
            selected = configuration is ConfigChildComponent.Home,
        )
        NavigationBarItem(
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
                selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                unselectedTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                indicatorColor = MaterialTheme.colorScheme.onBackground
            ),
            onClick = {
                component.onTabClick(ConfigChildComponent.Favorites)
            },
            icon = {
                Icon(
                    imageVector =  BottomNavItem.Favorite.icon,
                    contentDescription = BottomNavItem.Home.route,
                    modifier = Modifier.size(22.dp)
                )
            },
            label = {
                Text(text = stringResource(BottomNavItem.Home.title), fontSize = 12.sp)
            },
            selected = configuration is ConfigChildComponent.Favorites,
        )
    }
}