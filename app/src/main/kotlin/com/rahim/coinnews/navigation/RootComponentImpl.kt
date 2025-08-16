package com.rahim.coinnews.navigation

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.KoinComponent

class RootComponentImpl(componentContext: ComponentContext) : RootComponent,
    ComponentContext by componentContext, KoinComponent {
}