package com.example.tmscompose.network

import kotlinx.serialization.Serializable

const val RESPONSE_CODE_SUCCESS = "1"
const val REQUEST_SUCCESS = "REQUEST_SUCCESS"

@Serializable
data class TmsApiResult<T>(
    var code: String? = "",
    val desc: String? = "",
    val hasMore: Boolean? = false,
    private val data: T? = null,
    val recordsTotal: Int? = 0,
    val recordsFiltered: Int? = 0
) {
    fun apiData(): T {
        if (data != null && code == RESPONSE_CODE_SUCCESS) {
            return data
        } else {
            throw ApiException(code?.toIntOrNull() ?: -1, desc ?: "")
        }
    }

    fun apiNoData(): T? {
        if (code == RESPONSE_CODE_SUCCESS) {
            return data
        } else {
            throw ApiException(code?.toIntOrNull() ?: -1, desc ?: "")
        }
    }

    fun apiErrorNoToast(): String? {
        return if (code == RESPONSE_CODE_SUCCESS) REQUEST_SUCCESS else desc
    }
}

//data class TmsApiErrorResult(
//    @SerializedName("code") var code: String?,
//    @SerializedName("desc") val desc: String?
//) {
//    fun apiErrorData() {
//        if (code != RESPONSE_CODE_SUCCESS) {
//            throw ApiException(code?.toIntOrNull() ?: -1, desc ?: "")
//        }
//    }
//}