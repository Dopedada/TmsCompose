package com.example.tmscompose.ext

import androidx.lifecycle.viewModelScope
import com.example.tmscompose.base.BaseViewModel
import io.ktor.client.network.sockets.SocketTimeoutException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

typealias Block<T> = suspend () -> T
typealias Error = suspend (e: Exception) -> Unit
typealias Cancel = suspend (e: Exception) -> Unit

fun <T> BaseViewModel.request(
    block: Block<Unit>,
    error: Error? = null,
    cancel: Cancel? = null,
    showErrorToast: Boolean = true
): Job {
    return viewModelScope.launch {
        try {
            block.invoke()
        } catch (e: Exception) {
            when (e) {
                is CancellationException -> {
                    cancel?.invoke(e)
                }
                else -> {
                    if (showErrorToast) {
                        errorMessage = when (e) {
//                            is ApiException -> e.message
//                            is ConnectException, is UnknownHostException -> "网络走丢了,请查看手机网络状态"
                            is SocketTimeoutException -> "网络拥堵,稍后请重试"
                            else -> "未知错误"
                        }
                    }
                    error?.invoke(e)
                }
            }
        }
    }
}