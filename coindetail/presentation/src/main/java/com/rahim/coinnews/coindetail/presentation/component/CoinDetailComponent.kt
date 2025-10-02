package com.rahim.coinnews.coindetail.presentation.component

import androidx.compose.runtime.Immutable
import com.rahim.coinnews.core.utils.UnidirectionalComponent

interface CoinDetailComponent :
    UnidirectionalComponent<CoinDetailComponent.Event, CoinDetailComponent.State> {

    @Immutable
    sealed class Event {
    }

    @Immutable
    data class State(val data: String = "")
}