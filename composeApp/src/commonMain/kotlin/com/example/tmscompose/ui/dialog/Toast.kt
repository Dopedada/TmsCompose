package com.example.tmscompose.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import kotlinx.coroutines.delay

@Composable
fun GlobalToast(content: String, onHide: () -> Unit) {
    LaunchedEffect(content) {
        delay(1300)
        onHide.invoke()
    }
    val density = LocalDensity.current

    Popup(
        alignment = Alignment.BottomCenter,
        offset = with(density) { IntOffset(0, -100.dp.toPx().toInt()) }
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = Color.Black.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = content,
                color = Color.White,
                fontSize = 14.sp,
            )
        }
    }
}