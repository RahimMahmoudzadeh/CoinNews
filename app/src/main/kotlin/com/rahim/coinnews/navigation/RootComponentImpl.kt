package com.rahim.coinnews.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.rahim.coinnews.library.navigation.config.ConfigChildComponent
import org.koin.core.component.KoinComponent

class RootComponentImpl(componentContext: ComponentContext) : RootComponent,
    ComponentContext by componentContext, KoinComponent {
    private val navigation = StackNavigation<ConfigChildComponent>()

    override fun onTabClick(tab: ConfigChildComponent) {
        navigation.bringToFront(tab)
    }
}