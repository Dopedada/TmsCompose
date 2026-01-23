package com.example.tmscompose.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel() : ViewModel() {

    protected val _loadDialog = MutableStateFlow(false)
    val isShowLoadDialog = _loadDialog.asStateFlow()

    // 错误消息
    var errorMessage by mutableStateOf<String?>(null)

}