package com.example.tmscompose.ui.dialog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf

class GlobalManager {
    // 管理Dialog状态
    var dialogState by mutableStateOf(GlobalDialogState())
        private set

    // 显示Dialog
    fun showDialog(
        dialogType: DialogType,
        title: String = "",
        content: String = "",
        confirmText: String = "确定",
        cancelText: String = "取消",
        onConfirm: () -> Unit = {},
        onCancel: () -> Unit = {},
        onDismiss: () -> Unit = {}
    ) {
        dialogState = GlobalDialogState(
            dialogType = dialogType,
            isShow = true,
            title = title,
            content = content,
            confirmText = confirmText,
            cancelText = cancelText,
            onConfirm = onConfirm,
            onCancel = onCancel,
            onDismiss = onDismiss
        )
    }

    fun showToast(content: String = "") {
        dialogState = GlobalDialogState(
            dialogType = DialogType.TOAST,
            isShow = true,
            content = content
        )
    }

    fun dismissDialog() {
        dialogState = dialogState.copy(isShow = false)
        dialogState.onDismiss.invoke()
    }

}

val LocalGlobalDialogManager = staticCompositionLocalOf { GlobalManager() }