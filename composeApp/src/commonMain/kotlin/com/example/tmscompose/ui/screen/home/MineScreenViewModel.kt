package com.example.tmscompose.ui.screen.home

import com.example.tmscompose.base.BaseViewModel
import com.example.tmscompose.ext.request
import com.example.tmscompose.network.Repository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MineScreenViewModel (private val repository: Repository) : BaseViewModel() {

    private val _userAvatar = MutableStateFlow("")
    val userAvatar = _userAvatar.asStateFlow()

    private val _identity = MutableStateFlow("")
    val identity = _identity.asStateFlow()

    private val _logisticsName = MutableStateFlow("")
    val logisticsName = _logisticsName.asStateFlow()

    private val _userMobile = MutableStateFlow("")
    val userMobile = _userMobile.asStateFlow()

    init {
        request<Job>(block = {
//            val loginUserDao = TmsDatabase.getInstance().loginUersDao().getUserInfo()
//            val logisticsUser = loginUserDao?.logisticsLoginDto?.logisticsUser
//
//            _userMobile.value = logisticsUser?.userMobile.hideMiddleFourDigits()
//            _userAvatar.value = logisticsUser?.userImage ?: ""
//            _identity.value =
//                "${loginUserDao?.logisticsLoginDto?.logisticsRole?.roleName ?: ""} | ${logisticsUser?.userName ?: ""}"
//            _logisticsName.value = loginUserDao?.logisticsLoginDto?.logistics?.logisticsName ?: ""
        })

    }

    fun logout() {

    }

}