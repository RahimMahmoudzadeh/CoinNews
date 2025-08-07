package com.rahim.coinnews.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.value.Value
import kotlin.Unit

data class StateDispatch<EVENT, STATE>(
    val state: STATE,
    val event: (EVENT) -> Unit,
)

interface UnidirectionalComponent<EVENT, STATE : Any> {
    val state: Value<STATE>
    fun event(event: EVENT)
}

@Composable
inline fun <reified EVENT, STATE : Any> use(component: UnidirectionalComponent<EVENT, STATE>): StateDispatch<EVENT, STATE> {
    val state by component.state.subscribeAsState()

    val dispatch: (EVENT) -> Unit = { event ->
        component.event(event)
    }
    return StateDispatch(
        state = state,
        event = dispatch,
    )
}

