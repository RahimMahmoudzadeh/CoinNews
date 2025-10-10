package com.rahim.coinnews.home.presentation.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.essenty.lifecycle.doOnCreate
import com.rahim.coinnews.core.utils.Errors
import com.rahim.coinnews.core.utils.LoadableData
import com.rahim.coinnews.core.utils.Resource
import com.rahim.coinnews.domain.useCase.DeleteFavoriteUseCase
import com.rahim.coinnews.domain.useCase.GetMarketsUseCase
import com.rahim.coinnews.domain.useCase.SaveFavoriteUseCase
import com.rahim.coinnews.home.presentation.mapper.toMarketDomain
import com.rahim.coinnews.home.presentation.mapper.toMarketPresentationLayer
import com.rahim.coinnews.home.presentation.model.MarketPresentationLayer
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeComponentImpl(
    componentContext: ComponentContext,
    mainContext: CoroutineContext,
    private val onNavigateDetailScreen: (String) -> Unit,
    private val getMarketsUseCase: GetMarketsUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
) :
    HomeComponent,
    ComponentContext by componentContext {

    private val scopeSupervisorJob: CoroutineScope = coroutineScope(mainContext + SupervisorJob())
    private val scope: CoroutineScope = coroutineScope(mainContext)

    private val _state = MutableValue(HomeComponent.State())
    override val state: Value<HomeComponent.State> = _state

    init {
        lifecycle.doOnCreate {
            getMarketList()
        }
    }

    override fun event(event: HomeComponent.Event) = when (event) {
        is HomeComponent.Event.OnGetMarketList -> {
            getMarketList()
        }

        is HomeComponent.Event.OnFavoriteClick -> {
            onFavoriteClick(marketModel = event.market)
        }

        is HomeComponent.Event.OnNavigateDetailScreen -> onNavigateDetailScreen(event.idCoin)
    }

    private fun onFavoriteClick(marketModel: MarketPresentationLayer) {
        scopeSupervisorJob.launch {
            if (marketModel.isFavorite) {
                deleteFavorite(marketModel = marketModel)
            } else {
                saveFavorite(marketModel = marketModel)
            }
        }
    }

    private suspend fun saveFavorite(marketModel: MarketPresentationLayer) {
        saveFavoriteUseCase(marketModel.toMarketDomain())
    }

    private suspend fun deleteFavorite(marketModel: MarketPresentationLayer) {
        deleteFavoriteUseCase(market = marketModel.toMarketDomain())
    }

    private fun getMarketList() {
        getMarketsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data.let { markets ->
                        val markets =
                            markets.map { it.toMarketPresentationLayer() }.toPersistentList()
                        _state.update {
                            it.copy(marketList = LoadableData.Loaded(data = markets))
                        }
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            marketList = LoadableData.Error(error = result.error),
                        )
                    }
                }
            }
        }.catch { exception ->
            _state.update {
                it.copy(
                    marketList = LoadableData.Error(
                        error = Errors.ExceptionError(message = exception.message),
                    ),
                )
            }
        }.launchIn(scope)
    }
}