package com.example.tmscompose.ui.dialog

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.tmscompose.theme.Color333333
import com.example.tmscompose.ui.components.CancelButton
import com.example.tmscompose.ui.components.ConfirmButton
import org.jetbrains.compose.resources.painterResource
import tmscompose.composeapp.generated.resources.Res
import tmscompose.composeapp.generated.resources.loading_anim

@Composable
fun GlobalDialog() {
    val dialogManager = LocalGlobalDialogManager.current
    val dialogState = dialogManager.dialogState
    if (dialogState.isShow) {
        when (dialogState.dialogType) {
            DialogType.LOADING -> LoadDialog()
            DialogType.CONFIRM -> ConfirmDialog(
                title = dialogState.title,
                content = dialogState.content,
                cancelText = dialogState.cancelText,
                confirmText = dialogState.confirmText,
                onDismissRequest = {
                    dialogManager.dismissDialog()
                },
                onCancelClick = {
                    dialogState.onCancel.invoke()
                    dialogManager.dismissDialog()
                },
                onConfirmClick = {
                    dialogState.onConfirm.invoke()
                    dialogManager.dismissDialog()
                }
            )

            DialogType.TOAST -> GlobalToast(
                content = dialogState.content
            ) { dialogManager.dismissDialog() }
        }
    }
}

@Composable
fun ConfirmDialog(
    title: String = "标题",
    content: String = "内容",
    cancelText: String = "取消",
    confirmText: String = "确认",
    onDismissRequest: () -> Unit,
    onCancelClick: () -> Unit,
    onConfirmClick: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties()
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            shape = RoundedCornerShape(16.dp),
            shadowElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 标题
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black,
                    fontSize = 17.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = content,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color333333,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    CancelButton(
                        modifier = Modifier.weight(1f),
                        onClick = onCancelClick,
                        text = cancelText
                    )
                    ConfirmButton(
                        modifier = Modifier.weight(1f),
                        onClick = onConfirmClick,
                        text = confirmText
                    )
                }
            }
        }
    }
}

@Composable
fun LoadDialog() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    Dialog(onDismissRequest = { }) {
        Image(
            painter = painterResource(Res.drawable.loading_anim),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .graphicsLayer(rotationZ = rotation)
        )
    }
}

@Preview
@Composable
fun preview() {
    Column(modifier = Modifier.fillMaxSize()) {
        ConfirmDialog(onConfirmClick = {}, onCancelClick = {}, onDismissRequest = {})
    }
}

@Preview
@Composable
fun preview2() {
    Column(modifier = Modifier.fillMaxSize()) {
        LoadDialog()
    }
}

