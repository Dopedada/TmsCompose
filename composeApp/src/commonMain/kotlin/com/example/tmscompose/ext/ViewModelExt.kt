package com.example.tmscompose.ext

import androidx.lifecycle.viewModelScope
import com.example.tmscompose.base.BaseViewModel
import com.example.tmscompose.network.ApiException
import com.example.tmscompose.util.logE
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
                            is ApiException -> e.message
                            is SocketTimeoutException -> "网络拥堵,稍后请重试"
                            else -> e.message
                        }
                        errorMessage?.logE()
                    }
                    error?.invoke(e)
                }
            }
        }
    }
}