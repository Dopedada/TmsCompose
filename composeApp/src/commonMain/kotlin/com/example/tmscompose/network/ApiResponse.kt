package com.example.tmscompose.network

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)