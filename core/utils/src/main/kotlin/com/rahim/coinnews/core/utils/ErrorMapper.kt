package com.rahim.coinnews.core.utils

fun errorViewMapper(errors: Errors): String {
    return when (errors) {
        is Errors.ApiError -> {
            errors.message ?: "Unknown Error"
        }

        is Errors.ExceptionError -> {
            errors.message ?: "Unknown Error"
        }
    }
}
