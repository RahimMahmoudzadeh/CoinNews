package com.rahim.coinnews.presentation.navigation

import androidx.compose.runtime.Immutable
import com.rahim.coinnews.core.utils.LoadableData
import com.rahim.coinnews.core.utils.UnidirectionalComponent
import com.rahim.coinnews.presentation.model.MarketPresentationLayer
import kotlinx.collections.immutable.PersistentList

interface HomeComponent :
    UnidirectionalComponent<HomeComponent.Event, HomeComponent.State> {
    @Immutable
    sealed class Event {
        data class OnSetShowFavoriteList(val showFavoriteList: Boolean) : Event()
        data class OnFavoriteClick(val market: MarketPresentationLayer) : Event()
        data object OnGetMarketList : Event()
    }

    @Immutable
    data class State(
        val marketList: LoadableData<PersistentList<MarketPresentationLayer>> = LoadableData.Initial,
        val showFavoriteList: Boolean = false,
    )
}