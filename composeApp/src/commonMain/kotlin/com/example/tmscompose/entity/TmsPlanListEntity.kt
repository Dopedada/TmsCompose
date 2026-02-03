package com.example.tmscompose.entity

import kotlinx.serialization.Serializable

@Serializable
data class TmsPlanListEntity(
    val license: String?,
    val OrderPlan: OrderPlan?,
    val OrderRoute: OrderRoute?,
    val MaterialUnit: MaterialUnit?,
    val DeliveryWay: DeliveryWay?,
    val OrderAdjustPrice: OrderAdjustPrice?,
//    val ContractMaterial: PlanMaterial?,
    val OrderAdjustNum: OrderAdjustNum?,
)

@Serializable
data class OrderAdjustNum(
    val createTime: String?,
    val createName: String?,
    val adjustDesc: String?,
    val orderNumAfter: Double?,
    val orderNumBefore: Double?,
)

//@Serializable
//data class PlanMaterial(
//    val materialName: String?,
//    val materialNums: Double?,
//    val planNums: Double?,
//    val materialUnit: String?,
//    val contractId: Int?,
//    val totalSendNums: Double?,
//    val updateUserName: String?,
//    val createUserName: String?,
//    val updateTime: String?,
//    val totalNums: Double?
//) : Serializable

@Serializable
data class OrderAdjustPrice(
    val transportPriceAfter: Double?,
    val afterOverflowPrice: Double?,
    val afterLossPrice: Double?,
    val afterLossNums: Double?,
    val afterOverflowNums: Double?,
    val createName: String?,
    val adjustDesc: String?,
)

@Serializable
data class MaterialUnit(
    val materialUnitName: String?
)

@Serializable
data class DeliveryWay(
    val deliveryWayName: String?
)

@Serializable
data class OrderPlan(
    val orderCode: String?,
    val qrCode: String?,
    val materialName: String?,
    val createTime: String?,
    val orderName: String?,
    val updateTime: String?,
    val updateUsername: String?,
    val materialUnitName: String?,
    val totalSendnums: Double?,
    val totalLoading: Double?,
    val goodsUnitPrice: Double?,
    val overflowPrice: Double?,
    val lossPrice: Double?,
    val overflowNums: Double?,
    val payUnitPrice: Double?,
    val lossNums: Double?,
    val totalStock: Double?,
    val maxSendOut: Double?,
    val orderStatus: Int?,
    val orderPaytypeId: Int?,
    val chargingRuleId: Int?
)

@Serializable
data class OrderRoute(
//    val loadFence: Int?,
//    val loadFenceMiles: Double?,
    val loadingAddress: String?,
//    val loadingAreaId: Int?,
//    val loadingCompany: String?,
//    val loadingCompanyCode: String?,
//    val loadingContacts: String?,
//    val loadingContactsTel: String?,
//    val loadingLatitude: String?,
//    val loadingLongitude: String?,
//    val routeDesc: String?,
    val routeMiles: Double?,
    val routeName: String?,
//    val unlaodContacts: String?,
    val unloadAddress: String?,
//    val unloadAreaId: Int?,
//    val unloadCompanyCode: String?,
//    val unloadContactsTel: String?,
//    val unloadFence: Int?,
//    val unloadFenceMiles: Double?,
//    val unloadLatitude: String?,
//    val unloadLongitude: String?
)