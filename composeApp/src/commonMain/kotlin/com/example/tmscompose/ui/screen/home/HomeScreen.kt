package com.example.tmscompose.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.tmscompose.theme.Color1873D4
import com.example.tmscompose.theme.Color999999
import com.example.tmscompose.theme.ColorF2F5F9
import com.example.tmscompose.theme.gradientColors
import com.example.tmscompose.ui.components.LazyColumnWithFooterView
import com.example.tmscompose.ui.components.MImage
import com.example.tmscompose.ui.components.SwipeRefresh
import com.example.tmscompose.ui.components.layout.CoordinatorLayout
import com.example.tmscompose.ui.components.layout.rememberCoordinatorState
import com.example.tmscompose.ui.components.noRippleClickable
import com.example.tmscompose.ui.items.HomeItems
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import tmscompose.composeapp.generated.resources.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = koinViewModel(),
) {
    val hasMoreData by viewModel.hasMoreData.collectAsState()
    SwipeRefresh(
        modifier = Modifier.fillMaxSize(),
        isRefreshing = viewModel.isRefreshing.collectAsState().value,
        isLoading = viewModel.isLoadingMore.collectAsState().value,
        loadMoreEnabled = hasMoreData,
        onRefresh = { viewModel.refreshData() },
        onLoadMore = { viewModel.loadMoreData() }) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            val lazyListState = rememberLazyListState()
            val coordinatorState = rememberCoordinatorState()

            val titleBarAlpha by remember {
                derivedStateOf {
                    if (coordinatorState.maxCollapsableHeight == 0f) 0f
                    else (coordinatorState.collapsedHeight / coordinatorState.maxCollapsableHeight).coerceIn(0f, 1f)
                }
            }

            var nonCollapsableHeight by remember { mutableIntStateOf(0) }
            val list by viewModel.dataList.collectAsState()

            CoordinatorLayout(
                nestedScrollableState = { lazyListState },
                state = coordinatorState,
                modifier = Modifier.fillMaxSize(),
                collapsableContent = {
                    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                        val (image, row) = createRefs()
                        MImage(
                            modifier = Modifier
                                .fillMaxSize()
                                .constrainAs(image) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                },
                            contentScale = ContentScale.Crop,
                            painter = painterResource(Res.drawable.ic_home_bg)
                        )
                        HomeFeature(
                            modifier = Modifier
                                .constrainAs(row) {
                                    top.linkTo(image.bottom, margin = (-10).dp)
                                    start.linkTo(parent.start)
                                }
                                .fillMaxWidth()
                                .height(90.dp)
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                                )) { type ->
                            when (type) {
                                0 -> {}
                                1 -> {}
                                2 -> {}
                                3 -> {}
                            }
                        }
                    }
                },
                nonCollapsableHeight = nonCollapsableHeight,
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "自营计划",
                            color = Color1873D4,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            modifier = Modifier.noRippleClickable {},
                            text = "查看全部 >",
                            color = Color999999
                        )
                    }

                    LazyColumnWithFooterView(
                        modifier = Modifier
                            .background(color = ColorF2F5F9)
                            .fillMaxSize()
                            .padding(horizontal = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        state = lazyListState,
                        hasMoreData = hasMoreData,
                        data = list
                    ) { item, index ->
                        HomeItems(item)
                    }
                }
            }

            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .graphicsLayer {
                        alpha = titleBarAlpha
                    }
                    .onSizeChanged {
                        nonCollapsableHeight = it.height
                    }
                    .background(Brush.verticalGradient(colors = gradientColors)), title = {
                    Text(
                        //modifier = Modifier.padding(top = 18.dp),
                        text = "测试TODO",
                        color = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = titleBarAlpha
                        ),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )

        }
    }
}


@Composable
fun HomeFeature(modifier: Modifier = Modifier, onClick: (type: Int) -> Unit) {
    Row(
        modifier = modifier.padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MImage(
            modifier = Modifier.noRippleClickable { onClick.invoke(0) },
            painter = painterResource(Res.drawable.ic_home_contract),
            contentScale = ContentScale.Crop
        )
        MImage(
            modifier = Modifier.noRippleClickable { onClick.invoke(1) },
            painter = painterResource(Res.drawable.ic_home_plan)
        )
        MImage(
            modifier = Modifier.noRippleClickable { onClick.invoke(2) },
            painter = painterResource(Res.drawable.ic_home_car)
        )
        MImage(
            modifier = Modifier.noRippleClickable { onClick.invoke(3) },
            painter = painterResource(Res.drawable.ic_home_bill)
        )
    }
}
