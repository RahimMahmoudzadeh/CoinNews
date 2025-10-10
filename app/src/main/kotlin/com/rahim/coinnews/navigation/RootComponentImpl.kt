package com.rahim.coinnews.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import com.rahim.coinnews.coindetail.domain.useCase.GetMarketChartUseCase
import com.rahim.coinnews.coindetail.domain.useCase.GetMarketDetailUseCase
import com.rahim.coinnews.coindetail.presentation.navigation.CoinDetailComponent
import com.rahim.coinnews.coindetail.presentation.navigation.CoinDetailComponentImpl
import com.rahim.coinnews.domain.useCase.DeleteFavoriteUseCase
import com.rahim.coinnews.domain.useCase.GetMarketsUseCase
import com.rahim.coinnews.domain.useCase.SaveFavoriteUseCase
import com.rahim.coinnews.favorite.domain.useCase.GetFavoritesUseCase
import com.rahim.coinnews.favorite.presentation.navigation.FavoriteComponent
import com.rahim.coinnews.favorite.presentation.navigation.FavoriteComponentImpl
import com.rahim.coinnews.library.navigation.config.ConfigChildComponent
import com.rahim.coinnews.navigation.RootComponent.ChildStack.*
import com.rahim.coinnews.home.presentation.navigation.HomeComponent
import com.rahim.coinnews.home.presentation.navigation.HomeComponentImpl
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class RootComponentImpl(componentContext: ComponentContext) : RootComponent,
    ComponentContext by componentContext, KoinComponent {

    private val navigation = StackNavigation<ConfigChildComponent>()

    private fun childComponent(
        config: ConfigChildComponent, childComponentContext: ComponentContext
    ): RootComponent.ChildStack = when (config) {
        ConfigChildComponent.Home -> HomeChildStack(
            homeComponent(
                childComponentContext
            )
        )

        ConfigChildComponent.Favorites -> FavoriteChildStack(favoriteComponent(componentContext = childComponentContext))
        is ConfigChildComponent.Detail -> CoinDetailChildStack(
            coinDetailComponent(componentContext = childComponentContext, coinId = config.coinId)
        )
    }

    private val getMarketsUseCase: GetMarketsUseCase = get()
    private val saveFavoriteUseCase: SaveFavoriteUseCase = get()
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase = get()

    override val stack: Value<ChildStack<*, RootComponent.ChildStack>> = childStack(
        source = navigation,
        serializer = ConfigChildComponent.serializer(),
        initialConfiguration = ConfigChildComponent.Home,
        handleBackButton = true,
        childFactory = ::childComponent,
    )

    @OptIn(DelicateDecomposeApi::class)
    private fun homeComponent(componentContext: ComponentContext): HomeComponent =
        HomeComponentImpl(
            componentContext = componentContext,
            mainContext = Dispatchers.Main,
            getMarketsUseCase = getMarketsUseCase,
            saveFavoriteUseCase = saveFavoriteUseCase,
            deleteFavoriteUseCase = deleteFavoriteUseCase,
            onNavigateDetailScreen = { coinId ->
                navigation.pushNew(configuration = ConfigChildComponent.Detail(coinId = coinId))
            }
        )

    private val getMarketChartUseCase: GetMarketChartUseCase = get()
    private val getMarketDetailUseCase: GetMarketDetailUseCase = get()

    private fun coinDetailComponent(
        componentContext: ComponentContext,
        coinId: String
    ): CoinDetailComponent =
        CoinDetailComponentImpl(
            componentContext = componentContext,
            mainContext = Dispatchers.Main,
            getMarketChartUseCase = getMarketChartUseCase,
            getMarketDetailUseCase = getMarketDetailUseCase,
            coinId = coinId
        )

    private val getFavoritesUseCase: GetFavoritesUseCase = get()

    private fun favoriteComponent(componentContext: ComponentContext): FavoriteComponent =
        FavoriteComponentImpl(
            componentContext = componentContext,
            mainContext = Dispatchers.Main,
            getFavoritesUseCase = getFavoritesUseCase
        )

    override fun onTabClick(tab: ConfigChildComponent) {
        navigation.bringToFront(tab)
    }
}