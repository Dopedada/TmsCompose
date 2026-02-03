package com.example.tmscompose.network

import com.example.tmscompose.commom.getStoreString
import com.example.tmscompose.ui.screen.login.USER_TOKEN
import com.example.tmscompose.util.logD
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
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
                header("TOKEN", getStoreString(USER_TOKEN, ""))
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
