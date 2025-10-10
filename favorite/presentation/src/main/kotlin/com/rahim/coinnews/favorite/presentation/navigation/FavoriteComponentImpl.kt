package com.rahim.coinnews.favorite.presentation.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlin.coroutines.CoroutineContext

class FavoriteComponentImpl(mainContext: CoroutineContext, componentContext: ComponentContext) : FavoriteComponent,
    ComponentContext by componentContext {

    private val _state = MutableValue(FavoriteComponent.State())
    override val state: Value<FavoriteComponent.State> = _state

    override fun event(event: FavoriteComponent.Event) = when (event) {

        else -> {}
    }
}