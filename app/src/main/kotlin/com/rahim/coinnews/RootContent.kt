package com.rahim.coinnews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.rahim.coinnews.coindetail.presentation.CoinDetailRoute
import com.rahim.coinnews.navigation.RootComponent
import com.rahim.coinnews.home.presentation.HomeScreenRoute

@Composable
internal fun RootContent(modifier: Modifier = Modifier, component: RootComponent) {
    Children(
        stack = component.stack,
        modifier = modifier.fillMaxSize(),
        animation = stackAnimation(fade()),
    ) {
        Surface(color = MaterialTheme.colorScheme.background) {
            when (val child = it.instance) {
                is RootComponent.ChildStack.HomeChildStack -> HomeScreenRoute(component = child.homeComponent)
                is RootComponent.ChildStack.CoinDetailChildStack -> CoinDetailRoute(component = child.coinDetailComponent)
            }
        }
    }
}