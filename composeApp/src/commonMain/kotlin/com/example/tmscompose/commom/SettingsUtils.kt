package com.example.tmscompose.commom

import com.example.tmscompose.createSettings

private val settings = createSettings()

fun saveLong(key: String, value: Long) {
    settings.putLong(key, value)
}

fun getStoreLong(key: String, defaultValue: Long): Long {
    return settings.getLong(key, defaultValue)
}

fun saveString(key: String, value: String) {
    settings.putString(key, value)
}

fun getStoreString(key: String, defaultValue: String): String {
    return settings.getString(key, defaultValue)
}

fun saveBoolean(key: String, value: Boolean) {
    settings.putBoolean(key, value)
}

fun getStoreBoolean(key: String, defaultValue: Boolean = false): Boolean {
    return settings.getBoolean(key, defaultValue)
}
