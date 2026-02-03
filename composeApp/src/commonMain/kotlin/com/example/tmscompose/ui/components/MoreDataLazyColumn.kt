package com.example.tmscompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun <T> LazyColumnWithFooterView(
    modifier: Modifier = Modifier,
    hasMoreData: Boolean,
    data: List<T>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    state: LazyListState = rememberLazyListState(),
    dividerColor: Color = Color(0xFFF2F3F6),
    dividerHorizontalPadding: Int = 16,
    noMoreText: String = "没有更多数据了",
    noMoreTextColor: Color = Color(0xFF999999),
    noMoreTextSize: TextUnit = 15.sp,
    noMoreVerticalPadding: Int = 16,
    itemContent: @Composable (item: T, index: Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        contentPadding = contentPadding,
        state = state
    ) {
        // 渲染列表项
        items(data.size, key = { it }) { index ->
            itemContent(data[index], index)
//            // 最后一项不显示分割线（避免底部多一条线）
//            if (index != data.lastIndex) {
//                HorizontalDivider(
//                    modifier = Modifier.padding(horizontal = dividerHorizontalPadding.dp),
//                    color = dividerColor
//                )
//            }
        }

        if (!hasMoreData) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = noMoreText,
                        color = noMoreTextColor,
                        fontSize = noMoreTextSize,
                        modifier = Modifier.padding(vertical = noMoreVerticalPadding.dp)
                    )
                }
            }
        }
    }
}