package com.example.tmscompose.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tmscompose.theme.Color3B82F6
import com.example.tmscompose.theme.ColorLine

fun DrawScope.line() {
    drawLine(
        color = ColorLine,
        start = Offset(0f, size.height),
        end = Offset(size.width, size.height),
        strokeWidth = 0.5.dp.toPx()
    )
}

@Preview
@Composable
fun RoundRect() {
    Canvas(
        modifier = Modifier
            .width(4.dp)
            .height(14.dp)
    ) {
        drawRoundRect(
            cornerRadius = CornerRadius(1.dp.toPx()),
            color = Color3B82F6,
            size = Size(4.dp.toPx(), 14.dp.toPx())
        )
    }
}