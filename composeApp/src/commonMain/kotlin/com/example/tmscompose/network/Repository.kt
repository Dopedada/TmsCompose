package com.example.tmscompose.network

import com.example.tmscompose.entity.ImgVerifyEntity
import com.example.tmscompose.entity.LoginEntity
import io.ktor.client.HttpClient

class Repository(private val httpClient: HttpClient) {

    suspend fun loadImgVerifyCode(): TmsApiResult<ImgVerifyEntity> {
        return httpClient.getRequest<ImgVerifyEntity>("tms/login/imgCode")
    }

    suspend fun doLogisticsLogin(body: Map<String, Any?>): TmsApiResult<LoginEntity> {
        return httpClient.postRequest(path = "tms/login/doLogin", body = body)
    }

}