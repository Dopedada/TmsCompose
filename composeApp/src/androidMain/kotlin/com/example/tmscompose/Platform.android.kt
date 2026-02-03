package com.example.tmscompose

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import com.example.tmscompose.TmsApp.Companion.appContext
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import java.text.DecimalFormat

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun createSettings(): Settings {
    val delegate: SharedPreferences = appContext.getSharedPreferences("Tms", Context.MODE_PRIVATE)
    return SharedPreferencesSettings(delegate)
}

actual fun createHttpClientEngine(): HttpClientEngine {
    return OkHttp.create()
}

actual fun Double?.normalFormat(): String {
    return DecimalFormat("#.###############").format(this ?: 0.0f)
}

actual fun Double?.doubleToDecimalPlace(decimals: Int, def: Double): Double {
    if (this == null) return def
    return "%.${decimals}f".format(this).toDouble()
}

