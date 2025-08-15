package com.rahim.coinnews.presentation.navigation

import androidx.compose.runtime.Immutable
import com.rahim.coinnews.core.utils.LoadableData
import com.rahim.coinnews.core.utils.UnidirectionalComponent

interface HomeComponent :
    UnidirectionalComponent<HomeComponent.Event, HomeComponent.State> {
    @Immutable
    sealed class Event {
        data class OnSetShowFavoriteList(val showFavoriteList: Boolean) : Event()
        data class OnFavoriteClick(val market: MarketModel) : Event()
        data object OnGetMarketList : Event()
    }

    @Immutable
    data class State(
        val marketList: LoadableData<PersistentList<MarketModel>> = LoadableData.Initial,
        val showFavoriteList: Boolean = false,
    )
}