package com.example.tmscompose.base

import com.example.tmscompose.ext.request
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseListViewModel<T> : BaseViewModel() {

    protected val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore = _isLoadingMore.asStateFlow()
    protected val _hasMoreData = MutableStateFlow(true)
    val hasMoreData = _hasMoreData.asStateFlow()

    protected val _dataList = MutableStateFlow<MutableList<T>>(mutableListOf())
    val dataList = _dataList.asStateFlow()

    // 优化3：刷新逻辑 - 新增状态重置 + 原子性更新
    fun refreshData() {
        request<Job>(
            block = {
                _isRefreshing.value = true
                _hasMoreData.value = true // 刷新时重置“是否有更多”状态
                val newData = getRefreshData()
                // 原子更新，避免并发问题
                _dataList.update { newData }
                _isRefreshing.value = false
            },
            error = {
                _isRefreshing.value = false
                // 错误时清空列表，避免旧数据误导
                _dataList.update { mutableListOf() }
            }
        )
    }

    // 优化4：加载更多 - 避免重复添加 + 空数据判断更严谨
    fun loadMoreData() {
        // 防重复请求：加载中/无更多数据时直接返回
        if (_isLoadingMore.value || !_hasMoreData.value) return

        request<Job>(
            block = {
                _isLoadingMore.value = true
                val oldData = _dataList.value
                val moreData = getLoadMoreData(oldData.size)

                if (moreData.isEmpty()) {
                    _hasMoreData.value = false
                } else {
                    // 去重（可选，根据业务场景）
                    val distinctData = (oldData + moreData).distinct().toMutableList()
                    _dataList.update { distinctData }
                }
                _isLoadingMore.value = false
            },
            error = {
                _isLoadingMore.value = false
            }
        )
    }

    abstract suspend fun getRefreshData(): MutableList<T>

    abstract suspend fun getLoadMoreData(pageNum: Int): MutableList<T>
}
