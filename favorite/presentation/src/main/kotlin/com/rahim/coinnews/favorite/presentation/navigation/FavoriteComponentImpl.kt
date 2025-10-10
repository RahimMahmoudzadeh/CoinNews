package com.rahim.coinnews.favorite.presentation.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.essenty.lifecycle.doOnCreate
import com.rahim.coinnews.core.utils.Errors
import com.rahim.coinnews.core.utils.LoadableData
import com.rahim.coinnews.favorite.domain.useCase.GetFavoritesUseCase
import com.rahim.coinnews.favorite.presentation.mapper.toFavoriteCoinPresentationLayer
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FavoriteComponentImpl(
    mainContext: CoroutineContext,
    componentContext: ComponentContext,
    private val getFavoritesUseCase: GetFavoritesUseCase,
) :
    FavoriteComponent,
    ComponentContext by componentContext {

    private val scope: CoroutineScope = coroutineScope(mainContext + SupervisorJob())

    private val _state = MutableValue(FavoriteComponent.State())
    override val state: Value<FavoriteComponent.State> = _state

    init {
        lifecycle.doOnCreate {
            getFavorites()
        }
    }

    override fun event(event: FavoriteComponent.Event) = when (event) {
        else -> {}
    }

    private fun getFavorites() {
        _state.update {
            it.copy(marketList = LoadableData.Loading)
        }
        scope.launch {
            getFavoritesUseCase().catch { exception ->
                _state.update {
                    it.copy(marketList = LoadableData.Error(error = Errors.ExceptionError(message = exception.message)))
                }
            }.collect {
                val favorites = it.map {
                    it.toFavoriteCoinPresentationLayer()
                }.toPersistentList()
                _state.update {
                    it.copy(marketList = LoadableData.Loaded(favorites))
                }
            }
        }
    }
}