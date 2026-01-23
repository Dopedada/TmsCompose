package com.example.tmscompose.util

internal actual fun platformPrint(
    level: LogLevel,
    tag: String,
    message: String
) {
    println("$tag -- $message")
}