package com.rahim.coinnews.network.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.accept
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import org.koin.dsl.module

private const val BASE_URL = "api.coingecko.com/api/v3/"

val networkModule = module {
    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = true
        }
    }

    single {
        HttpClient(Android) {
            expectSuccess = true

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL
                }
                contentType(Json)
                accept(Json)
            }

        }
    }
}