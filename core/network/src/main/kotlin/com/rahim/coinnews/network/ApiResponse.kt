package com.rahim.coinnews.network

sealed interface ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>

    sealed interface Failure<T> : ApiResponse<T> {

        data class Error(val payload: Any?) : Failure<Nothing> {
            val message = payload.toString()
        }

        data class Exception(val throwable: Throwable) : Failure<Nothing> {
            val message: String? = throwable.message
        }
    }
}
