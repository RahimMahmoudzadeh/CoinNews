package com.rahim.coinnews.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.rahim.coinnews.domain.useCase.GetMarketsUseCase
import com.rahim.coinnews.library.navigation.config.ConfigChildComponent
import com.rahim.coinnews.navigation.RootComponent.ChildStack.*
import com.rahim.coinnews.presentation.navigation.HomeComponent
import com.rahim.coinnews.presentation.navigation.HomeComponentImpl
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class RootComponentImpl(componentContext: ComponentContext) : RootComponent,
    ComponentContext by componentContext, KoinComponent {

    private val navigation = StackNavigation<ConfigChildComponent>()

    private fun childComponent(
        config: ConfigChildComponent,
        childComponentContext: ComponentContext
    ): RootComponent.ChildStack =
        when (config) {
            ConfigChildComponent.Home -> HomeChildStack(
                homeComponent(
                    childComponentContext
                )
            )

            ConfigChildComponent.Favorites -> TODO()
        }

    override val stack: Value<ChildStack<*, RootComponent.ChildStack>> =
        childStack(
            source = navigation,
            serializer = ConfigChildComponent.serializer(),
            initialConfiguration = ConfigChildComponent.Home,
            handleBackButton = true,
            childFactory = ::childComponent,
        )

    private val getMarketsUseCase: GetMarketsUseCase = get()
    private fun homeComponent(componentContext: ComponentContext): HomeComponent =
        HomeComponentImpl(
            componentContext = componentContext,
            mainContext = Dispatchers.Main,
            getMarketsUseCase = getMarketsUseCase
        )

    override fun onTabClick(tab: ConfigChildComponent) {
        navigation.bringToFront(tab)
    }
}