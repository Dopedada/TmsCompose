package com.example.tmscompose

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import platform.Foundation.NSUserDefaults
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
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