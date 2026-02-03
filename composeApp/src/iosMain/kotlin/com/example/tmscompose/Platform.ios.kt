package com.example.tmscompose

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*
import platform.Foundation.*
import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun createSettings(): Settings {
    val userDefaults = NSUserDefaults.standardUserDefaults
    return NSUserDefaultsSettings(userDefaults)
}

actual fun createHttpClientEngine(): HttpClientEngine {
    return Darwin.create()
}

actual fun Double?.normalFormat(): String {
    if (this == null) return "0"
    val formatter = NSNumberFormatter().apply {
        maximumFractionDigits = 15.toULong()
        minimumFractionDigits = 15.toULong()
        numberStyle = NSNumberFormatterDecimalStyle
    }
    return formatter.stringFromNumber(NSNumber(this)) ?: this.toString()
}

actual fun Double?.doubleToDecimalPlace(decimals: Int, def: Double): Double {
    if (this == null) return def
    return NSString.stringWithFormat("%.${decimals}f", this).toDouble()
}