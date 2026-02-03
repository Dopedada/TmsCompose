package com.example.tmscompose

import com.russhwolf.settings.Settings
import io.ktor.client.engine.*

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun createSettings(): Settings

expect fun createHttpClientEngine(): HttpClientEngine

expect fun Double?.normalFormat(): String

expect fun Double?.doubleToDecimalPlace(decimals: Int, def: Double = 0.0): Double