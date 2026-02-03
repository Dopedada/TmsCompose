package com.example.tmscompose.entity

import kotlinx.serialization.Serializable

@Serializable
data class WorkMenuEntity(
    val menuId: Int,
    val menuName: String?,
    val menuIcon: String? = "",
    val subMenuList: MutableList<WorkMenuEntity>?
)