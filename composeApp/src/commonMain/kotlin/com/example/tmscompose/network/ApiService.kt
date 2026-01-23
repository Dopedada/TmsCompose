package com.example.tmscompose.network

object ApiService {
    private const val BASE_URL = "https://api.example.com"

    /**
     * POST 请求示例：用户登录
     */
//    suspend fun login(username: String, password: String): LoginResponse {
//        return try {
//            httpClient.post {
//                url("$BASE_URL/login")
//                // 设置 ContentType
//                contentType(ContentType.Application.Json)
//                // 设置请求体
//                setBody(LoginRequest(username, password))
//            }.body()
//        } catch (e: Exception) {
//            println("POST 请求失败：${e.message}")
//            // 异常时返回默认响应
//            LoginResponse(code = -1, msg = "网络请求失败：${e.message}", data = null)
//        }
//    }

}