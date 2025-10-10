package com.rahim.coinnews.coindetail.presentation.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.essenty.lifecycle.doOnCreate
import com.rahim.coinnews.coindetail.domain.useCase.GetMarketChartUseCase
import com.rahim.coinnews.coindetail.domain.useCase.GetMarketDetailUseCase
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
) :
    ComponentContext by componentContext,
    CoinDetailComponent {

    private val scope = coroutineScope(mainContext + SupervisorJob())

    private val _state = MutableValue(CoinDetailComponent.State())
    override val state: Value<CoinDetailComponent.State> = _state

    init {
        lifecycle.doOnCreate {
            getMarketDetail(id = coinId)
            getMarketChart(id = coinId)
        }
    }

    override fun event(event: CoinDetailComponent.Event){

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
}