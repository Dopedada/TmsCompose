package com.example.tmscompose.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.king.ultraswiperefresh.NestedScrollMode
import com.king.ultraswiperefresh.UltraSwipeRefresh
import com.king.ultraswiperefresh.indicator.classic.ClassicRefreshFooter
import com.king.ultraswiperefresh.indicator.classic.ClassicRefreshHeader

@Composable
fun SwipeRefresh(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    isLoading: Boolean,
    onRefresh: (() -> Unit)? = null,
    onLoadMore: (() -> Unit)? = null,
    refreshEnabled: Boolean = true,
    loadMoreEnabled: Boolean = true,
    content: @Composable () -> Unit
) {
    UltraSwipeRefresh(
        modifier = modifier,
        isRefreshing = isRefreshing,
        isLoading = isLoading,
        refreshEnabled = refreshEnabled,
        loadMoreEnabled = loadMoreEnabled,
        onRefresh = { onRefresh?.invoke() },
        onLoadMore = { onLoadMore?.invoke() },
        headerScrollMode = NestedScrollMode.Translate,
        footerScrollMode = NestedScrollMode.Translate,
        headerIndicator = {
            ClassicRefreshHeader(it)
        },
        footerIndicator = {
            ClassicRefreshFooter(it)
        }, content = content
    )
}