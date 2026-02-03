package com.example.tmscompose.ui.screen.home

import com.example.tmscompose.base.BaseListViewModel
import com.example.tmscompose.entity.TmsPlanListEntity
import com.example.tmscompose.ext.request
import com.example.tmscompose.network.Repository
import kotlinx.coroutines.Job

private const val FORM_ID = 3000117
private const val INIT_PAGE = 0

class HomeScreenViewModel(private val repository: Repository) : BaseListViewModel<TmsPlanListEntity>() {

    init {
        fetchHomePlanList()
    }

    fun fetchHomePlanList(page: Int = INIT_PAGE) {
        request<Job>(
            block = {
                val response = repository.getHomePlanList(formId = FORM_ID, start = page)
                _dataList.value = response.apiData().takeIf { it.isNotEmpty() } ?: mutableListOf()
            },
            error = {
                _dataList.value = mutableListOf() // 空数据兜底，避免UI层空指针
            }
        )
    }

    // 优化3：抽取重复逻辑为私有函数，减少冗余
    private suspend fun getPlanList(page: Int): MutableList<TmsPlanListEntity> {
        return runCatching {
            repository.getHomePlanList(formId = FORM_ID, start = page).apiData()
        }.getOrElse {
            mutableListOf()
        }
    }

    override suspend fun getRefreshData(): MutableList<TmsPlanListEntity> {
        return getPlanList(INIT_PAGE)
    }

    override suspend fun getLoadMoreData(pageNum: Int): MutableList<TmsPlanListEntity> {
        return getPlanList(pageNum)
    }

}