package com.rahim.coinnews.navigation

import com.arkivanov.decompose.value.Value
import com.rahim.coinnews.library.navigation.config.ConfigChildComponent
import com.rahim.coinnews.presentation.navigation.HomeComponent

interface RootComponent {
    val stack: Value<com.arkivanov.decompose.router.stack.ChildStack<*, ChildStack>>
    fun onTabClick(tab: ConfigChildComponent)
    sealed class ChildStack {
        data class HomeChildStack(val homeComponent: HomeComponent): ChildStack()
    }
}