package com.rahim.coinnews.coindetail.presentation.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.rahim.coinnews.coindetail.domain.useCase.GetMarketChartUseCase
import com.rahim.coinnews.coindetail.domain.useCase.GetMarketDetailUseCase
import com.rahim.coinnews.coindetail.domain.useCase.ToggleFavoriteMarketListUseCase
import com.rahim.coinnews.coindetail.presentation.mapper.toMarketChartPresentationLayerCoinDetail
import com.rahim.coinnews.coindetail.presentation.mapper.toMarketDetailPresentationLayerCoinDetail
import com.rahim.coinnews.coindetail.presentation.mapper.toMarketDomainLayerCoinDetail
import com.rahim.coinnews.coindetail.presentation.model.MarketPresentationLayerCoinDetail
import com.rahim.coinnews.core.utils.Errors
import com.rahim.coinnews.core.utils.LoadableData
import com.rahim.coinnews.core.utils.Resource
import com.rahim.coinnews.core.utils.base.onIO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CoinDetailComponentImpl(
    componentContext: ComponentContext,
    mainContext: CoroutineContext,
    private val coinId: String,
    private val getMarketChartUseCase: GetMarketChartUseCase,
    private val getMarketDetailUseCase: GetMarketDetailUseCase,
    private val toggleFavoriteMarketListUseCase: ToggleFavoriteMarketListUseCase,
) :
    ComponentContext by componentContext,
    CoinDetailComponent {

    private val scope = coroutineScope(mainContext + SupervisorJob())

    private val _state = MutableValue(CoinDetailComponent.State())
    override val state: Value<CoinDetailComponent.State> = _state

    override fun event(event: CoinDetailComponent.Event) = when (event) {
        is CoinDetailComponent.Event.SetMarket -> setMarket(market = event.market)
        is CoinDetailComponent.Event.OnFavoriteClick -> onFavoriteClick(market = event.market)
        is CoinDetailComponent.Event.GetMarketChart -> getMarketChart(id = event.marketId)
        is CoinDetailComponent.Event.GetMarketDetail -> getMarketDetail(id = event.marketId)
    }

    private fun getMarketDetail(id: String) {
        _state.update {
            it.copy(marketDetail = LoadableData.Loading)
        }

        getMarketDetailUseCase(id = id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data.let { detail ->
                        _state.update {
                            it.copy(marketDetail = LoadableData.Loaded(data = detail.toMarketDetailPresentationLayerCoinDetail()))
                        }
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            marketDetail = LoadableData.Error(error = result.error),
                        )
                    }
                }
            }
        }.catch { exception ->
            _state.update {
                it.copy(
                    marketDetail = LoadableData.Error(error = Errors.ExceptionError(message = exception.message)),
                )
            }
        }.launchIn(scope)
    }

    private fun setMarket(market: MarketPresentationLayerCoinDetail) {
        _state.update {
            it.copy(market = LoadableData.Loaded(market))
        }
    }

    private fun getMarketChart(id: String) {
        _state.update {
            it.copy(marketChart = LoadableData.Loading)
        }

        getMarketChartUseCase(id = id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data.let { chart ->
                        _state.update {
                            it.copy(marketChart = LoadableData.Loaded(data = chart.toMarketChartPresentationLayerCoinDetail()))
                        }
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            marketChart = LoadableData.Error(error = result.error),
                        )
                    }
                }
            }
        }.catch { exception ->
            _state.update {
                it.copy(
                    marketChart = LoadableData.Error(error = Errors.ExceptionError(message = exception.message)),
                )
            }
        }.launchIn(scope)
    }

    private fun onFavoriteClick(market: MarketPresentationLayerCoinDetail) {
        scope.launch {
            onIO {
//                toggleFavoriteMarketListUseCase(market.toMarketDomainLayerCoinDetail())
            }
            toggleFavoriteState()
        }
    }

    private fun toggleFavoriteState() {
        val market = (_state.value.market as LoadableData.Loaded).data
        val isFavorite = market.isFavorite
        val newMarket = LoadableData.Loaded(market.copy(isFavorite = isFavorite))
        _state.update { it.copy(market = newMarket) }
    }
}