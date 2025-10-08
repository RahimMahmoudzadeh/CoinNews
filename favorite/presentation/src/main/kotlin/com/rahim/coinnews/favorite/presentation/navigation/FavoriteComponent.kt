package com.rahim.coinnews.favorite.presentation.navigation

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.rahim.coinnews.core.utils.LoadableData
import com.rahim.coinnews.core.utils.UnidirectionalComponent
import com.rahim.coinnews.favorite.presentation.model.FavoriteCoinPresentationLayer
import kotlinx.collections.immutable.PersistentList

interface FavoriteComponent :
    UnidirectionalComponent<FavoriteComponent.Event, FavoriteComponent.State> {
    @Immutable
    sealed class Event {

    }

    @Stable
    data class State(
        val marketList: LoadableData<PersistentList<FavoriteCoinPresentationLayer>> = LoadableData.Initial,
    )
}