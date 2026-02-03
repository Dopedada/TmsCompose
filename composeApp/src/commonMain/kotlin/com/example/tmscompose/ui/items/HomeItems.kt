package com.example.tmscompose.ui.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tmscompose.doubleToDecimalPlace
import com.example.tmscompose.entity.TmsPlanListEntity
import com.example.tmscompose.normalFormat
import com.example.tmscompose.theme.Color3B82F6
import com.example.tmscompose.ui.components.MImage
import com.king.ultraswiperefresh.indicator.CircularProgressIndicator
import divideWithNoPrecision
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import tmscompose.composeapp.generated.resources.*

val gradientColors = listOf(Color(0xCCECF0F4), Color(0x33DEEDFD))

@Composable
fun HomeItems(item: TmsPlanListEntity? = null) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                MImage(
                    painter = painterResource(Res.drawable.ic_plan_num)
                )
                Text(
                    text = "计划号：${item?.OrderPlan?.orderCode ?: ""}",
                    fontSize = 14.sp,
                    color = Color(0xFF333333),
                    modifier = Modifier.padding(start = 6.dp)
                )
            }
            Text(
                text = item?.DeliveryWay?.deliveryWayName ?: "",
                fontSize = 14.sp,
                color = Color(0xFFFF9933),
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 地址路径
        Column(modifier = Modifier.fillMaxWidth()) {
            // 起始地址
            AddressItem(
                icon = painterResource(Res.drawable.ic_plan_start),
                address = item?.OrderRoute?.loadingAddress ?: ""
            )
            // 路径连接线
            Canvas(modifier = Modifier.size(16.dp, 32.dp)) {
                drawLine(
                    color = Color(0xFFE5E8EF),
                    start = Offset(8f, 0f),
                    end = Offset(8f, size.height),
                    strokeWidth = 2f
                )
            }
            // 目的地址
            AddressItem(
                icon = painterResource(Res.drawable.ic_plan_end),
                address = item?.OrderRoute?.unloadAddress ?: ""
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 信息卡片
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(colors = gradientColors),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // 线路信息
                InfoRow(
                    icon = painterResource(Res.drawable.ic_plan_line_road),
                    label = "线路",
                    value = item?.OrderRoute?.routeName ?: ""
                )
                InfoRow(
                    icon = painterResource(Res.drawable.ic_plan_line_road),
                    label = "货物",
                    value = item?.OrderPlan?.materialName ?: ""
                )

                val materialUnit = item?.MaterialUnit?.materialUnitName ?: ""
                val maxSend = item?.OrderPlan?.maxSendOut ?: 0.0
                val totalLoading = item?.OrderPlan?.totalLoading.normalFormat()

                val text = buildAnnotatedString {
                    // 假设原始资源是 "总量 A 已发 B"
                    val baseString = stringResource(Res.string.cargo_quantity)

                    // 你可以根据关键字拆分，或者简单拼接
                    append("总量 ")
                    append("$maxSend $materialUnit")
                    append(" ")

                    withStyle(style = SpanStyle(color = Color(0xFF1873D4))) {
                        append("已发 $totalLoading $materialUnit")
                    }
                }

                InfoRow(
                    icon = painterResource(Res.drawable.ic_plan_line_road),
                    label = "货量",
                    value = text.toString()

                )
                InfoRow(
                    icon = painterResource(Res.drawable.ic_plan_line_road),
                    label = "时间",
                    value = item?.OrderPlan?.createTime ?: ""
                )
            }
            Box(modifier = Modifier.size(48.dp), contentAlignment = Alignment.Center) {
                val d = item?.OrderPlan?.maxSendOut?.divideWithNoPrecision(
                    item.OrderPlan.totalStock ?: 0.0
                ) ?: 0.0
                CircularProgressIndicator(
                    progress = maxOf(
                        d.doubleToDecimalPlace(1).toFloat(), 0f
                    ),
                    color = Color(0xFF4080FF),
                    strokeWidth = 4.dp
                )
                Text(text = "${(d * 100).toString()}%", color = Color3B82F6, fontSize = 11.sp)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 底部按钮
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = {},
                border = BorderStroke(1.dp, Color(0xFF4080FF)),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.padding(end = 12.dp)
            ) {
                Text(
                    text = "二维码派车",
                    color = Color(0xFF4080FF),
                    fontSize = 14.sp
                )
            }
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4080FF)),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = "派车",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}

// 地址项子组件
@Composable
private fun AddressItem(
    icon: Painter,
    address: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        MImage(
            painter = icon,
            contentDescription = "地址",
            modifier = Modifier
                .size(16.dp)
                .padding(top = 2.dp)
        )
        Text(
            text = address,
            fontSize = 14.sp,
            color = Color(0xFF333333),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

// 信息行子组件
@Composable
private fun InfoRow(
    icon: Painter,
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        MImage(
            painter = icon,
            contentDescription = label,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "$label | ",
            fontSize = 12.sp,
            color = Color(0xFF999999),
            modifier = Modifier.padding(start = 4.dp)
        )
        Text(
            text = value,
            fontSize = 12.sp,
            color = Color(0xFF333333),
            modifier = Modifier.padding(end = 8.dp)
        )
    }

}


@Preview
@Composable
fun ItemsPreview() {
    HomeItems()
}