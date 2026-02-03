package com.example.tmscompose.ui.screen.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tmscompose.ScreenRoute
import com.example.tmscompose.ui.components.MImage
import com.example.tmscompose.ui.components.noRippleClickable
import com.example.tmscompose.ui.screen.home.HomeScreen
import com.example.tmscompose.ui.screen.home.MineScreen
import com.example.tmscompose.ui.screen.home.WorkScreen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import tmscompose.composeapp.generated.resources.Res
import tmscompose.composeapp.generated.resources.ic_home_sel
import tmscompose.composeapp.generated.resources.ic_mine_slec
import tmscompose.composeapp.generated.resources.ic_work_slec

object NavBarConstants {
    val SelectedColor = Color(0xFF1873D4) // Color1873D4
    val UnselectedColor = Color(0xFF7B8EA2) // Color7B8EA2
    val BottomPadding = 8.dp
}

enum class NavItem(
    val index: Int,
    val iconRes: DrawableResource? = null,
    val label: String? = null
) {
    Home(0, Res.drawable.ic_home_sel, "首页"),
    Work(1),
    Mine(2, Res.drawable.ic_mine_slec, "我的")
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentSelectedIndex = remember { mutableIntStateOf(0) }
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (topContent, bottomNav, workBtn) = createRefs()
        Box(
            modifier = Modifier
                .constrainAs(topContent) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(bottomNav.top)
                    height = Dimension.fillToConstraints
                },
            contentAlignment = Alignment.Center
        ) {

            NavHost(
                navController = navController,
                startDestination = ScreenRoute.Home.route,
                enterTransition = { fadeIn(animationSpec = tween(300)) },
                exitTransition = { fadeOut(animationSpec = tween(300)) }
            ) {
                composable(route = ScreenRoute.Home.route) {
                    HomeScreen()
                }
                composable(ScreenRoute.Work.route) {
                    WorkScreen(navController)
                }
                composable(ScreenRoute.Mine.route) {
                    MineScreen(navController)
                }
            }
        }
        BottomNavBar(
            modifier = Modifier
                .height(80.dp)
                .constrainAs(bottomNav) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }, onItemSelected = { index ->
                navigateToScreen(navController, index)
                currentSelectedIndex.intValue = index
            }, selectedIndex = currentSelectedIndex.intValue
        )

        MImage(
            modifier = Modifier
                .size(56.dp)
                .noRippleClickable {
                    navigateToScreen(navController, 1)
                    currentSelectedIndex.intValue = 1
                }
                .constrainAs(workBtn) {
                    start.linkTo(bottomNav.start)
                    end.linkTo(bottomNav.end)
                    bottom.linkTo(bottomNav.top)
                    top.linkTo(bottomNav.top)
                    translationY = 10.dp
                }, painter = painterResource(Res.drawable.ic_work_slec)
        )
    }
}

private fun navigateToScreen(navController: NavHostController, index: Int) {
    val targetRoute = when (index) {
        0 -> ScreenRoute.Home.route
        1 -> ScreenRoute.Work.route
        2 -> ScreenRoute.Mine.route
        else -> ScreenRoute.Home.route
    }

    if (navController.currentDestination?.route == targetRoute) return

    // 核心：导航时配置状态保存/恢复
    navController.navigate(targetRoute) {

        // 1. 弹出到起始页，保存所有出栈页面的状态
        popUpTo(navController.graph.startDestinationId) {
            saveState = true // ✅ 保存被弹出页面的状态（关键）
            inclusive = false // 不包含起始页，避免首页被弹出
        }
        // 2. 保证页面单例，避免重复创建
        launchSingleTop = true
        // 3. 恢复目标页面之前保存的状态（关键）
        restoreState = true
    }
}

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    onItemSelected: (Int) -> Unit = {}
) {


    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = NavBarConstants.BottomPadding),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        NavItem.entries.forEach { item ->
            val itemColor by animateColorAsState(
                targetValue = if (selectedIndex == item.index) {
                    NavBarConstants.SelectedColor
                } else {
                    NavBarConstants.UnselectedColor
                }
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .noRippleClickable {
                        if (item.index != 1) {
                            onItemSelected(item.index)
                        }
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (item != NavItem.Work) {
                    MImage(
                        painter = painterResource(item.iconRes!!),
                        colorFilter = ColorFilter.tint(itemColor)
                    )
                    Text(
                        text = item.label!!,
                        color = itemColor,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}