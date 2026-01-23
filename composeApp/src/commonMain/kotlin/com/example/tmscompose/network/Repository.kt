package com.example.tmscompose.network

import com.example.tmscompose.entity.ImgVerifyEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class Repository(private val httpClient: HttpClient) {

    suspend fun getArticles(page: Int = 0): ApiResponse<String> {
        return httpClient.get("/article/list/$page/json").body()
    }

    suspend fun loadImgVerifyCode(): TmsApiResult<ImgVerifyEntity> {
        return httpClient.get("tms/login/imgCode").body()
    }

}