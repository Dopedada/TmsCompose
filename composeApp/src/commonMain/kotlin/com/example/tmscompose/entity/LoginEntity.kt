package com.example.tmscompose.entity

import kotlinx.serialization.Serializable

@Serializable
data class LoginEntity(
    val token: String?,
    val logisticsLoginDto: LogisticsLogin?,
)

@Serializable
data class LogisticsLogin(
    val logisticsRole: LogisticsRole?,
    val logistics: Logistics?,
    val logisticsUser: LogisticsUser?
)

@Serializable
data class LogisticsRole(
    val roleName: String?
)

@Serializable
data class Logistics(
    val logisticsName: String?
)

@Serializable
data class LogisticsUser(
    var userName: String?,
    var userMobile: String?,
    val logisticsUserId: Int?,
    val logisticsId: Int?,
    val userLoginName: String?,
    var userImage: String?
)
