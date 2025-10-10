package com.rahim.coinnews.navigation

import com.arkivanov.decompose.value.Value
import com.rahim.coinnews.coindetail.presentation.navigation.CoinDetailComponent
import com.rahim.coinnews.favorite.presentation.navigation.FavoriteComponent
import com.rahim.coinnews.library.navigation.config.ConfigChildComponent
import com.rahim.coinnews.home.presentation.navigation.HomeComponent

interface RootComponent {
    val stack: Value<com.arkivanov.decompose.router.stack.ChildStack<*, ChildStack>>

    fun onTabClick(tab: ConfigChildComponent)

    sealed class ChildStack {
        data class HomeChildStack(val homeComponent: HomeComponent): ChildStack()
        data class CoinDetailChildStack(val coinDetailComponent: CoinDetailComponent): ChildStack()
        data class FavoriteChildStack(val favoriteComponent: FavoriteComponent): ChildStack()
    }
}