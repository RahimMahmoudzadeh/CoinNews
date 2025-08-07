package com.rahim.coinnews.presentation.navigation

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class HomeComponentImpl : HomeComponent {
    private val _state = MutableValue(HomeComponent.State())
    override val state: Value<HomeComponent.State> = _state

    override fun event(event: HomeComponent.HomeEvent) {
        TODO("Not yet implemented")
    }
}