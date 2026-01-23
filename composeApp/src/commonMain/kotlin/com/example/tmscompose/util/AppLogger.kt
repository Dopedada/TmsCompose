package com.example.tmscompose.util

enum class LogLevel() {
    DEBUG,
    INFO,
    WARN,
    ERROR
}

const val TAG = "TmsCompose"

fun String.logD(tag: String = TAG) {
    platformPrint(LogLevel.DEBUG, tag, this)
}

fun String.logI(tag: String = TAG) {
    platformPrint(LogLevel.INFO, tag, this)
}

fun String.logW(tag: String = TAG) {
    platformPrint(LogLevel.WARN, tag, this)
}

fun String.logE(tag: String = TAG, throwable: Throwable? = null) {
    platformPrint(LogLevel.ERROR, tag, "$this ${throwable?.stackTraceToString() ?: ""}")
}

internal expect fun platformPrint(level: LogLevel, tag: String, message: String)