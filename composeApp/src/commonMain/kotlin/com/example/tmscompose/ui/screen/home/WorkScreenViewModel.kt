package com.example.tmscompose.ui.screen.home

import com.example.tmscompose.base.BaseViewModel
import com.example.tmscompose.entity.WorkMenuEntity
import com.example.tmscompose.ext.request
import com.example.tmscompose.network.Repository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WorkScreenViewModel(private val repository: Repository) :
    BaseViewModel() {
    private val _workMenu = MutableStateFlow<MutableList<WorkMenuEntity>>(
        mutableListOf()
    )
    val workMenu = _workMenu.asStateFlow()

    init {
        request<Job>(block = {
            val apiData = repository.getMenuList().apiData()

            val filter = apiData.filter { !it.subMenuList.isNullOrEmpty() }.toMutableList()

            val data = mutableListOf<WorkMenuEntity>().apply {
                add(
                    WorkMenuEntity(
                        menuId = -10,
                        menuName = "全部",
                        subMenuList = filter
                    )
                )

                val subMenus = filter
                    .map { item ->
                        val displayName = item.menuName.orEmpty()
                        WorkMenuEntity(
                            menuId = -10,
                            menuName = displayName,
                            subMenuList = mutableListOf(
                                WorkMenuEntity(
                                    menuId = -10,
                                    menuName = displayName,
                                    subMenuList = item.subMenuList
                                )
                            )
                        )
                    }

                addAll(subMenus)
            }
            _workMenu.value = data
        })
    }

}