package com.rahim.coinnews.presentation.navigation

import androidx.compose.runtime.Immutable
import com.rahim.coinnews.core.utils.UnidirectionalComponent

interface HomeComponent :
    UnidirectionalComponent<HomeComponent.HomeEvent, HomeComponent.State> {
    @Immutable
    sealed class HomeEvent {

    }

    @Immutable
    data class State(
        val data: String = "",
    )
}