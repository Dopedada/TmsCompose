package com.example.tmscompose.ui.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.tmscompose.IS_LOGIN
import com.example.tmscompose.base.BaseViewModel
import com.example.tmscompose.commom.getStoreString
import com.example.tmscompose.commom.saveBoolean
import com.example.tmscompose.commom.saveString
import com.example.tmscompose.entity.ImgVerifyEntity
import com.example.tmscompose.ext.request
import com.example.tmscompose.network.Repository
import com.example.tmscompose.util.logE
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json
import kotlin.io.encoding.Base64

const val USER_TOKEN = "user_token"
const val USER_INFO = "user_info"

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

    private val _loginSuccess = MutableStateFlow(false)
    val isLoginSuccess = _loginSuccess.asStateFlow()

    // 账号
    var account by mutableStateOf("")
        private set

    // 图片验证码
    var imgVerificationCode by mutableStateOf("")
        private set

    // 密码
    var pwd by mutableStateOf("")
        private set

    // 输入图片验证码
    fun updateImgVerificationCode(code: String) {
        imgVerificationCode = code
    }

    // 更新账号
    fun updateAccount(code: String) {
        account = code
    }

    // 更新密码
    fun updatePwd(code: String) {
        pwd = code
    }

    init {
        //图片验证码
        getLoadImgVerifyCode()
        account = getStoreString(ACCOUNT, "cdoub")
    }

    //图片验证码
    fun getLoadImgVerifyCode() {
        //图片验证码
        request<Job>(block = {
            imageVerifyEntity = repository.loadImgVerifyCode().apiData()
            _imgVerifyFlow.value = imageVerifyEntity.img.let {
                try {
                    Base64.decode(it)
                } catch (e: Exception) {
                    null
                }
            }
        }, error = {
            it.toString().logE()
        })
    }

    //密码登陆
    fun doLogisticsLogin() {
        errorMessage = when {
            account.isEmpty() -> "请输入账号"
            imgVerificationCode.isEmpty() -> "请输入图形验证码"
            pwd.isEmpty() -> "请输入密码"
            else -> {
                var errorMsg: String? = null
                _loadDialog.value = true
                request<Job>(block = {
                    val body = mapOf<String, Any?>(
                        "loginName" to account,
                        "pwd" to pwd,
                        "verifyCode" to imgVerificationCode,
                        "verifyKey" to imageVerifyEntity.verifyKey
                    )
                    val loginEntity = repository.doLogisticsLogin(body).apiData()

                    //存入登录用户信息
//                    TmsDatabase.getInstance().loginUersDao().insertLoginUser(loginEntity)
                    val js = Json {
                        // 可选配置，根据需求调整
                        ignoreUnknownKeys = true // 反序列化时忽略未知字段（序列化时无影响）
                        isLenient = true // 允许宽松的JSON格式（如尾随逗号）
                        prettyPrint = true // 格式化输出JSON（便于阅读，生产环境可关闭）
                        encodeDefaults = true // 序列化时包含默认值字段（默认开启）
                    }
                    val encodeToString = js.encodeToString(loginEntity)
                    saveString(USER_INFO, encodeToString)
                    saveBoolean(IS_LOGIN, true)
                    //存入用户token
                    saveString(USER_TOKEN, loginEntity.token ?: "")
                    saveString(ACCOUNT, account)
                    _loadDialog.value = false
                    _loginSuccess.value = true
                }, error = {
                    getLoadImgVerifyCode()
                    _loadDialog.value = false
                    errorMsg = it.message
                })
                errorMsg
            }
        }
    }


}