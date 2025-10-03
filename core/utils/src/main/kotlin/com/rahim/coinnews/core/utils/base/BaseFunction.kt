package com.rahim.coinnews.core.utils.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


suspend fun <T> onUI(action: suspend () -> T): T {
    return withContext(Dispatchers.Main) {
        action()
    }
}

suspend fun <T> onBg(action: suspend () -> T): T {
    return withContext(Dispatchers.Default) {
        action()
    }
}

suspend fun <T> onIO(action: suspend () -> T): T {
    return withContext(Dispatchers.IO) {
        action()
    }
}