package com.example.tmscompose.entity

import kotlinx.serialization.Serializable

@Serializable
data class TmsPlanListEntity(
    val license: String? = null,
    val OrderPlan: OrderPlan? = null,
    val OrderRoute: OrderRoute? = null,
    val MaterialUnit: MaterialUnit? = null,
    val DeliveryWay: DeliveryWay? = null,
    val OrderAdjustPrice: OrderAdjustPrice? = null,
//    val ContractMaterial: PlanMaterial?=null,
    val OrderAdjustNum: OrderAdjustNum? = null,
)

@Serializable
data class OrderAdjustNum(
    val createTime: String? = null,
    val createName: String? = null,
    val adjustDesc: String? = null,
    val orderNumAfter: Double? = null,
    val orderNumBefore: Double? = null,
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
    val transportPriceAfter: Double? = null,
    val afterOverflowPrice: Double? = null,
    val afterLossPrice: Double? = null,
    val afterLossNums: Double? = null,
    val afterOverflowNums: Double? = null,
    val createName: String? = null,
    val adjustDesc: String? = null,
)

@Serializable
data class MaterialUnit(
    val materialUnitName: String? = null
)

@Serializable
data class DeliveryWay(
    val deliveryWayName: String? = null
)

@Serializable
data class OrderPlan(
    val orderCode: String? = null,
    val qrCode: String? = null,
    val materialName: String? = null,
    val createTime: String? = null,
    val orderName: String? = null,
    val materialUnitName: String? = null,
    val totalSendnums: Double? = null,
    val totalLoading: Double? = null,
    val goodsUnitPrice: Double? = null,
    val overflowPrice: Double? = null,
    val lossPrice: Double? = null,
    val overflowNums: Double? = null,
    val payUnitPrice: Double? = null,
    val lossNums: Double? = null,
    val totalStock: Double? = null,
    val maxSendOut: Double? = null,
    val orderStatus: Int? = null,
    val orderPaytypeId: Int? = null,
    val chargingRuleId: Int? = null
)

@Serializable
data class OrderRoute(
//    val loadFence: Int?,
//    val loadFenceMiles: Double?,
    val loadingAddress: String? = null,
//    val loadingAreaId: Int?,
//    val loadingCompany: String?,
//    val loadingCompanyCode: String?,
//    val loadingContacts: String?,
//    val loadingContactsTel: String?,
//    val loadingLatitude: String?,
//    val loadingLongitude: String?,
//    val routeDesc: String?,
    val routeMiles: Double? = null,
    val routeName: String? = null,
//    val unlaodContacts: String?,
    val unloadAddress: String? = null,
//    val unloadAreaId: Int?,
//    val unloadCompanyCode: String?,
//    val unloadContactsTel: String?,
//    val unloadFence: Int?,
//    val unloadFenceMiles: Double?,
//    val unloadLatitude: String?,
//    val unloadLongitude: String?
)