package com.example.tmscompose.ui.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.tmscompose.base.BaseViewModel
import com.example.tmscompose.entity.ImgVerifyEntity
import com.example.tmscompose.ext.request
import com.example.tmscompose.network.Repository
import com.example.tmscompose.util.logE
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.io.encoding.Base64

class LoginViewModel(private val repository: Repository) : BaseViewModel() {

    private val _imgVerifyFlow = MutableStateFlow<ByteArray?>(null)
    val imgVerify = _imgVerifyFlow.asStateFlow()

    var imageVerifyEntity by mutableStateOf(
        ImgVerifyEntity(
            img = "",
            verifyKey = ""
        )
    )
        private set
//
//    private val _loginSuccess = MutableStateFlow(false)
//    val isLoginSuccess = _loginSuccess.asStateFlow()
//
//    // 账号
//    var account by mutableStateOf("")
//        private set
//
//    // 图片验证码
//    var imgVerificationCode by mutableStateOf("")
//        private set
//
//    // 密码
//    var pwd by mutableStateOf("")
//        private set
//
//    // 输入图片验证码
//    fun updateImgVerificationCode(code: String) {
//        imgVerificationCode = code
//    }
//
//    // 更新账号
//    fun updateAccount(code: String) {
//        account = code
//    }
//
//    // 更新密码
//    fun updatePwd(code: String) {
//        pwd = code
//    }
//
//    init {
//        // 初始化时从SP加载保存的数据
//        account = getSpValue(ACCOUNT, "")
//    }

    init {
        //图片验证码
        request<Job>(block = {
            imageVerifyEntity = repository.loadImgVerifyCode().apiData()
          val s =   imageVerifyEntity.img.let { it: String ->
                try {
                    Base64.decode(it)
                } catch (e: Exception) {
                    null
                }
            }
            s.toString().logE()
            _imgVerifyFlow.value = s
//            repository.getArticles()
        }, error = {
            it.toString().logE()
        })
    }

//    //密码登陆
//    fun doLogisticsLogin() {
//        errorMessage = when {
//            account.isEmpty() -> "请输入账号"
//            imgVerificationCode.isEmpty() -> "请输入图形验证码"
//            pwd.isEmpty() -> "请输入密码"
//            else -> {
//                _loadDialog.value = true
//                request<Job>(block = {
//                    val body = mutableMapOf<String, Any?>(
//                        "loginName" to account,
//                        "pwd" to pwd,
//                        "verifyCode" to imgVerificationCode,
//                        "verifyKey" to _imgVerifyFlow.value?.verifyKey
//                    )
//                    val loginEntity = apiService.doLogisticsLogin(body).apiData()
//
//                    //存入登录用户信息
//                    TmsDatabase.getInstance().loginUersDao().insertLoginUser(loginEntity)
//                    //存入用户token
//                    putSpValue(USER_TOKEN, loginEntity.token)
//                    putSpValue(ACCOUNT, account)
//                    _loadDialog.value = false
//                    _loginSuccess.value = true
//                }, error = {
//                    getLoadImgVerifyCode()
//                    _loadDialog.value = false
//                })
//                null
//            }
//        }
//    }

    fun getData() {
        request<Job>(block = {
            repository.getArticles()
        })
    }

}