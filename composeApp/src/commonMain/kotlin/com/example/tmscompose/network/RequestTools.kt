package com.example.tmscompose.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

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

suspend inline fun <reified T> HttpClient.postRequestForEncoded(
    path: String,
    body: Map<String, Any>? = null,
    params: Map<String, String>? = null
): TmsApiResult<T> {
    return try {
        post(path) {
            contentType(ContentType.Application.FormUrlEncoded)
            params?.forEach {
                parameter(it.key, it.value)
            }

            if (body != null) {
                // 必须将 Map 转换为 Parameters 才能被 FormUrlEncoded 识别
                setBody(FormDataContent(Parameters.build {
                    body.forEach { (key, value) ->
                        append(key, value.toString())
                    }
                }))
            }
        }.body()
    } catch (e: Exception) {
        TmsApiResult<T>(code = ERROR_CODE, desc = e.message)
    }
}