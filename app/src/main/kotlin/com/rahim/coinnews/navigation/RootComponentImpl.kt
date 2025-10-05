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
import com.rahim.coinnews.coindetail.domain.useCase.ToggleFavoriteMarketListUseCase
import com.rahim.coinnews.coindetail.presentation.navigation.CoinDetailComponent
import com.rahim.coinnews.coindetail.presentation.navigation.CoinDetailComponentImpl
import com.rahim.coinnews.domain.useCase.GetMarketsUseCase
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

        ConfigChildComponent.Favorites -> TODO()
        is ConfigChildComponent.Detail -> CoinDetailChildStack(
            coinDetailComponent(componentContext = childComponentContext, coinId = config.coinId)
        )
    }

    private val getMarketsUseCase: GetMarketsUseCase = get()

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
            onNavigateDetailScreen = { coinId ->
                navigation.pushNew(configuration = ConfigChildComponent.Detail(coinId = coinId))
            }
        )

    private val getMarketChartUseCase: GetMarketChartUseCase = get()
    private val getMarketDetailUseCase: GetMarketDetailUseCase = get()
    private val toggleFavoriteMarketListUseCase: ToggleFavoriteMarketListUseCase = get()

    private fun coinDetailComponent(componentContext: ComponentContext,coinId: String): CoinDetailComponent =
        CoinDetailComponentImpl(
            componentContext = componentContext,
            mainContext = Dispatchers.Main,
            getMarketChartUseCase = getMarketChartUseCase,
            getMarketDetailUseCase = getMarketDetailUseCase,
            toggleFavoriteMarketListUseCase = toggleFavoriteMarketListUseCase,
            coinId = coinId
        )

    override fun onTabClick(tab: ConfigChildComponent) {
        navigation.bringToFront(tab)
    }
}