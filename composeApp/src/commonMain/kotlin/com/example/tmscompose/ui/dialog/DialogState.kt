package com.example.tmscompose.ui.dialog

enum class DialogType {
    LOADING, CONFIRM, TOAST
}

data class GlobalDialogState(
    val isShow: Boolean = false,          // 是否显示Dialog
    val dialogType: DialogType = DialogType.LOADING,
    val title: String = "",               // 标题
    val content: String = "",             // 内容
    val confirmText: String = "确定",      // 确认按钮文字
    val cancelText: String = "取消",       // 取消按钮文字
    val onConfirm: () -> Unit = {},       // 确认回调
    val onCancel: () -> Unit = {},        // 取消回调
    val onDismiss: () -> Unit = {}        // 消失回调（点击空白处/返回键）
)
