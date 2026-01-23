package com.example.tmscompose.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.tmscompose.theme.Color1873D4
import com.example.tmscompose.theme.Color333333
import com.example.tmscompose.theme.Color3B82F6
import com.example.tmscompose.theme.Color999999
import com.example.tmscompose.theme.ColorC1C1C1
import com.example.tmscompose.theme.ColorF7F7F7
import com.example.tmscompose.ui.components.TextInputField
import com.example.tmscompose.ui.components.line
import com.example.tmscompose.ui.components.noRippleClickable
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.getKoin
import tmscompose.composeapp.generated.resources.Res
import tmscompose.composeapp.generated.resources.ic_login_bg

const val ACCOUNT = "account"

@Composable
fun LoginScreen(viewModel: LoginViewModel = getKoin().get(), navController: NavController) {
//    val dialogManager = LocalGlobalDialogManager.current
    val imgVerify = viewModel.imgVerify.collectAsState()
//    val isShowLoadDialog by viewModel.isShowLoadDialog.collectAsState()
//    val isLoginSuccess by viewModel.isLoginSuccess.collectAsState()
//    LaunchedEffect(isShowLoadDialog) {
//        if (isShowLoadDialog) {
//            dialogManager.showDialog(dialogType = DialogType.LOADING)
//        } else {
//            dialogManager.dismissDialog()
//        }
//    }
//
//
//    LaunchedEffect(viewModel.errorMessage) {
//        viewModel.errorMessage?.let {
//            dialogManager.showToast(it)
//            viewModel.errorMessage = null
//        }
//    }

//    LaunchedEffect(isLoginSuccess) {
//        if (isLoginSuccess) {
//            startClick.invoke()
//        }
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(Res.drawable.ic_login_bg),
            contentScale = ContentScale.FillWidth,
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 22.dp)
        ) {
            LoginInput(
                label = "账号",
                account = "viewModel.account",
                onValueChange = {
                    //viewModel.updateAccount(it)
                })
            ImageVerify(
                imgVerify = imgVerify,
                refreshClick = {
                    //viewModel.getLoadImgVerifyCode()
                },
                imgVerifyChange = {
                    // viewModel.updateImgVerificationCode(it)
                })
            LoginInput(
                label = "密码",
                visualTransformation = PasswordVisualTransformation(),
                keyboardType = KeyboardType.Password,
                onValueChange = {
                    //        viewModel.updatePwd(it)
                }
            )
            Spacer(modifier = Modifier.padding(top = 15.dp))
            Text(
                modifier = Modifier
                    .align(Alignment.End)
                    .noRippleClickable {

                    },
                text = "忘记密码",
                color = Color1873D4,
            )
            Spacer(modifier = Modifier.padding(top = 35.dp))
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color3B82F6),
                onClick = {
                    viewModel.getData()
//                    viewModel.doLogisticsLogin()
                }) {
                Text(
                    text = "登 录",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600
                )
            }
        }
    }
}

@Composable
fun LoginInput(
    modifier: Modifier = Modifier,
    label: String,
    account: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    maxLength: Int? = null,
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    onSetValue: ((MutableState<String>) -> Unit)? = null,
    onValueChange: (String) -> Unit
) {
    val valueState = remember { mutableStateOf(account) }
    onSetValue?.invoke(valueState)
    Row(
        modifier = modifier
            .drawBehind { line() }
            .fillMaxWidth()
            .padding(top = 12.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(modifier = Modifier.padding(start = 4.dp), text = label)
            TextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 3.dp),
                value = valueState.value,
                visualTransformation = visualTransformation,
                readOnly = readOnly,
                onValueChange = {
                    if (maxLength != null && it.length > maxLength) {
                        return@TextInputField
                    }
                    onValueChange(it)
                    valueState.value = it
                },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = Color333333,
                ),
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                singleLine = true,
                placeholder = {
                    Text(
                        modifier = modifier.fillMaxWidth(),
                        text = "请输入${label}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = Color999999
                    )
                },
                trailingIcon = trailingIcon
            )
        }
    }
}

@Composable
fun ImageVerify(
    imgVerify: State<ByteArray?>? = null,
    refreshClick: () -> Unit,
    imgVerifyChange: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .drawBehind { line() }
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
        LoginInput(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            label = "图形验证码",
            onValueChange = imgVerifyChange
        )
        Box(
            modifier = Modifier
                .size(90.dp, 48.dp)
                .background(
                    shape = RoundedCornerShape(8.dp),
                    brush = Brush.horizontalGradient(listOf(ColorC1C1C1, ColorF7F7F7))
                )
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { refreshClick() },
                model = imgVerify?.value,
                contentDescription = "",
                onError = {
//                    it.result.toString().logE()
                }
            )

        }
    }
}

