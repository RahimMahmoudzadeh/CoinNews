package com.rahim.coinnews.presentation.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class HomeComponentImpl(componentContext: ComponentContext, mainContext: CoroutineContext) :
    HomeComponent,
    ComponentContext by componentContext {

    private val scope: CoroutineScope = coroutineScope(mainContext + SupervisorJob())
    private val _state = MutableValue(HomeComponent.State())
    override val state: Value<HomeComponent.State> = _state

    override fun event(event: HomeComponent.Event) = when (event) {
        is HomeComponent.Event.OnGetMarketList -> {
//            getData()
        }

        is HomeComponent.Event.OnFavoriteClick -> {
//            onFavoriteClick(marketModel = event.market)
        }

        is HomeComponent.Event.OnSetShowFavoriteList -> onSetShowFavoriteList(
            showFavoriteList = event.showFavoriteList,
        )
    }

    private fun onSetShowFavoriteList(showFavoriteList: Boolean) {
        _state.update {
            it.copy(showFavoriteList = showFavoriteList)
        }
    }
//
//    private fun getData() {
//        scope.launch {
//            _state.update {
//                it.copy(marketList = LoadableData.Loading)
//            }
//
//            if (_state.value.showFavoriteList) {
//                getFavoriteMarketList()
//            } else {
//                getMarketList()
//            }
//        }
//    }

//    private suspend fun getMarketList() = getMarketListUseCase().onEach { newList ->
//        val marketList = newList.map { it.toMarketModel() }.toPersistentList()
//
//        _state.update {
//            it.copy(marketList = LoadableData.Loaded(data = marketList))
//        }
//    }.catch { exception ->
//        _state.update {
//            it.copy(
//                marketList = LoadableData.Error(
//                    error = Errors.ExceptionError(message = exception.message),
//                ),
//            )
//        }
//    }.launchIn(scope)
//
//    private fun getFavoriteMarketList() = getFavoriteMarketListUseCase().onEach { newList ->
//        val marketList = newList.map { it.toMarketModel() }.toPersistentList()
//
//        _state.update {
//            it.copy(marketList = LoadableData.Loaded(data = marketList))
//        }
//    }.launchIn(scope)
//
//    private fun onFavoriteClick(marketModel: MarketModel) {
//        _state.launch {
//            onIO {
//                toggleFavoriteMarketListUseCase(marketModel.toMarket())
//            }
//        }
//    }
}