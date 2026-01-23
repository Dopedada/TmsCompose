package com.example.tmscompose.network

import com.example.tmscompose.commom.getStoreString
import com.example.tmscompose.util.logD
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

const val baseUrl = "http://8.146.199.121:9093/"

val clientModule = module {
    single {
        HttpClient {
            defaultRequest {
                url(baseUrl)
                header("CLIENT", "2")
                header("VERSION", "1")
                header("TOKEN", getStoreString("TOKEN", ""))
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 10000
                connectTimeoutMillis = 10000
                connectTimeoutMillis = 10000
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        message.logD(tag = "HttpClient")
                    }
                }
                level = LogLevel.ALL
            }
        }
    }
}

val repositoryModule = module {
    single<Repository> { Repository(get()) }
}
