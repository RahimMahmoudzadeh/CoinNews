package com.rahim.coinnews.coindetail.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlin.coroutines.CoroutineContext

class CoinDetailComponentImpl(componentContext: ComponentContext, mainContext: CoroutineContext) :
    ComponentContext by componentContext,
    CoinDetailComponent {

    private val _state = MutableValue(CoinDetailComponent.State())
    override val state: Value<CoinDetailComponent.State> = _state

    override fun event(event: CoinDetailComponent.Event) = when (event) {

        else -> {}
    }
}