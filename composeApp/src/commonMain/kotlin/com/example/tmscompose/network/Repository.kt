package com.example.tmscompose.network

import com.example.tmscompose.entity.ImgVerifyEntity
import com.example.tmscompose.entity.LoginEntity
import com.example.tmscompose.entity.TmsPlanListEntity
import com.example.tmscompose.entity.WorkMenuEntity
import io.ktor.client.*

class Repository(private val httpClient: HttpClient) {

    suspend fun loadImgVerifyCode(): TmsApiResult<ImgVerifyEntity> {
        return httpClient.getRequest<ImgVerifyEntity>("tms/login/imgCode")
    }

    suspend fun doLogisticsLogin(body: Map<String, Any?>): TmsApiResult<LoginEntity> {
        return httpClient.postRequest(path = "tms/login/doLogin", body = body)
    }

    suspend fun getHomePlanList(
        formId: Int = 3000117,
        statusId: Int = 1,
        start: Int,
        length: Int = 10
    ): TmsApiResult<MutableList<TmsPlanListEntity>> {
        return httpClient.postRequestForEncoded(
            path = "v2/driver/select/doSearchByForm",
            body = mapOf("formId" to formId, "statusId" to statusId, "start" to start, "length" to length)
        )
    }

    suspend fun getMenuList(): TmsApiResult<MutableList<WorkMenuEntity>> {
        return httpClient.getRequest(path = "tms/app/menu/getUserMenu")
    }

}