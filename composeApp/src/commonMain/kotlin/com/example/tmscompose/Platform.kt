package com.example.tmscompose

import com.russhwolf.settings.Settings
import io.ktor.client.engine.HttpClientEngine

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun createSettings(): Settings

expect fun createHttpClientEngine(): HttpClientEngine
