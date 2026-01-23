package com.example.tmscompose.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

const val ERROR_CODE = "-999"

suspend inline fun <reified T> HttpClient.getRequest(
    path: String,
    params: Map<String, String>? = null
): TmsApiResult<T> {
    return try {
        get(path) {
            params?.forEach {
                parameter(it.key, it.value)
            }
        }.body()
    } catch (e: Exception) {
        TmsApiResult<T>(code = ERROR_CODE, desc = e.message)
    }
}

suspend inline fun <reified T> HttpClient.postRequest(
    path: String,
    body: Any? = null,
    params: Map<String, String>? = null
): TmsApiResult<T> {
    return try {
        post(path) {
            contentType(ContentType.Application.Json)
            body?.let { setBody(it) }
            params?.forEach {
                parameter(it.key, it.value)
            }
        }.body()
    } catch (e: Exception) {
        TmsApiResult<T>(code = ERROR_CODE, desc = e.message)
    }
}