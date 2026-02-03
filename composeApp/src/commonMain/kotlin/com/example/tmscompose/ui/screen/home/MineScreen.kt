package com.example.tmscompose.ui.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.tmscompose.theme.Color333333
import com.example.tmscompose.theme.Color666666
import com.example.tmscompose.theme.ColorF2F5F9
import com.example.tmscompose.theme.ColorFFCCCCCC
import com.example.tmscompose.ui.components.MImage
import com.example.tmscompose.ui.components.line
import com.example.tmscompose.ui.components.noRippleClickable
import com.example.tmscompose.ui.dialog.DialogType
import com.example.tmscompose.ui.dialog.LocalGlobalDialogManager
import com.example.tmscompose.util.logE
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import tmscompose.composeapp.generated.resources.*

@Composable
fun MineScreen(
    navController: NavHostController? = null,
    viewModel: MineScreenViewModel = koinViewModel()
) {
    val userAvatar by viewModel.userAvatar.collectAsState()
    val identity by viewModel.identity.collectAsState()
    val logisticsName by viewModel.logisticsName.collectAsState()
    val userMobile by viewModel.userMobile.collectAsState()
    val dialogManager = LocalGlobalDialogManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ColorF2F5F9),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        ) {
            val (ivAvatar, tvCompany, tvIdentity) = createRefs()
            MImage(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(Res.drawable.ic_mine_header)
            )
            AsyncImage(
                modifier = Modifier
                    .size(56.dp)
                    .constrainAs(ivAvatar) {
                        start.linkTo(parent.start, margin = 14.dp)
                        bottom.linkTo(parent.bottom, margin = 17.dp)
                    }
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                model = userAvatar,
                placeholder = painterResource(Res.drawable.ic_user_avata),
                error = painterResource(Res.drawable.ic_user_avata),
                contentDescription = "",
                onError = {
                    it.result.toString().logE("AsyncImage")
                }
            )
            Text(
                modifier = Modifier.constrainAs(tvCompany) {
                    start.linkTo(ivAvatar.end, margin = 8.dp)
                    top.linkTo(ivAvatar.top)
                    bottom.linkTo(tvIdentity.top)
                },
                text = logisticsName,
                maxLines = 1,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                modifier = Modifier.constrainAs(tvIdentity) {
                    start.linkTo(tvCompany.start)
                    bottom.linkTo(ivAvatar.bottom)
                    top.linkTo(tvCompany.bottom)
                },
                text = identity,
                maxLines = 1,
                fontSize = 14.sp,
                color = Color.White
            )
        }

        Column(
            modifier = Modifier
                .padding(10.dp)
                .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                .padding(start = 16.dp, end = 16.dp)
        ) {
            MineEnter(title = "我的头像", painter = painterResource(Res.drawable.ic_mine_avatar)) {}
            MineEnter(
                title = "修改手机号",
                subtitle = userMobile,
                painter = painterResource(Res.drawable.ic_mine_phone)
            ) {}
            MineEnter(title = "支付密码", painter = painterResource(Res.drawable.ic_mine_pay_pwd)) {}
            MineEnter(
                title = "登录密码",
                painter = painterResource(Res.drawable.ic_mine_login_pwd)
            ) {}
            MineEnter(
                title = "清除缓存",
                isShowEnter = false,
                painter = painterResource(Res.drawable.ic_mine_clear)
            ) {}
            MineEnter(
                title = "检查更新",
                isShowLine = false,
                isShowEnter = false,
                painter = painterResource(Res.drawable.ic_mine_update)
            ) {}
        }
        Spacer(modifier = Modifier.height(60.dp))
        Button(
            onClick = {
                dialogManager.showDialog(
                    dialogType = DialogType.CONFIRM,
                    title = "提示",
                    content = "确定要退出登录？",
                    onConfirm = {
                        viewModel.logout()
                    })
            },
            modifier = Modifier.size(width = 200.dp, height = 48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(40.dp),
            border = BorderStroke(width = 0.5.dp, color = ColorFFCCCCCC)
        ) {
            Text(
                text = "退出登录",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color666666
            )
        }
    }
}

@Composable
fun MineEnter(
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(Res.drawable.ic_mime_company),
    title: String = "我的头像",
    subtitle: String = "",
    isShowLine: Boolean = true,
    isShowEnter: Boolean = true,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .noRippleClickable { onClick.invoke() }
            .drawBehind {
                if (isShowLine) line()
            }
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)) {

        MImage(
            modifier = Modifier.size(20.dp),
            painter = painter
        )
        Text(text = title, color = Color333333, fontSize = 16.sp)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = subtitle, color = Color666666, fontSize = 14.sp)
        if (isShowEnter) {
            MImage(
                painter = painterResource(Res.drawable.ic_enter)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MineScreenPreview() {
//    MineScreen()
    Column(
        modifier = Modifier
            .padding(10.dp)
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp)
    ) {
        MineEnter {}
    }
}