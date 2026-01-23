package com.example.tmscompose.commom

import com.example.tmscompose.createSettings

object SettingsUtils {
    private val settings = createSettings()

    fun save(key: String, value: Long) {
        settings.putLong(key, value)
    }

    fun get(key: String, defaultValue: Long): Long {
        return settings.getLong(key, defaultValue)
    }

    fun saveString(key: String, value: String) {
        settings.putString(key, value)
    }

    fun getString(key: String, defaultValue: String): String {
        return settings.getString(key, defaultValue)
    }

    fun saveBoolean(key: String, value: Boolean) {
        settings.putBoolean(key, value)
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return settings.getBoolean(key, defaultValue)
    }

}