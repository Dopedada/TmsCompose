package com.example.tmscompose.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.tmscompose.theme.Color3B82F6
import com.example.tmscompose.theme.Color666666
import com.example.tmscompose.theme.ColorLine

@Composable
fun ConfirmButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String = "确定",
    containerColor: Color = Color3B82F6,
    textColor: Color = Color.White
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = containerColor)
    ) {
        Text(text = text, color = textColor)
    }
}

@Composable
fun CancelButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String = "取消",
    containerColor: Color = ColorLine,
    textColor: Color = Color666666
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = containerColor)
    ) {
        Text(text = text, color = textColor)
    }
}

@Preview
@Composable
fun ButtonPreview() {
    Column {
        ConfirmButton(onClick = {})
        CancelButton(onClick = {})
    }
}