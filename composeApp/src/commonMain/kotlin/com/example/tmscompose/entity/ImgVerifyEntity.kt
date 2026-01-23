package com.example.tmscompose.entity

import kotlinx.serialization.Serializable

@Serializable
data class ImgVerifyEntity(
    val img: String,
    val verifyKey: String
)
