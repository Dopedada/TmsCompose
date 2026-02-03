package com.example.tmscompose.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.contentPaddingWithLabel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.tmscompose.entity.WorkMenuEntity
import com.example.tmscompose.theme.*
import com.example.tmscompose.ui.components.MImage
import com.example.tmscompose.ui.components.TextInputField
import com.example.tmscompose.ui.items.gradientColors
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import tmscompose.composeapp.generated.resources.Res
import tmscompose.composeapp.generated.resources.ic_search

@Composable
fun WorkScreen(
    navController: NavHostController? = null, viewModel: WorkScreenViewModel = koinViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val workMenu by viewModel.workMenu.collectAsState()
        val coroutineScope = rememberCoroutineScope()
        val pagerState = rememberPagerState(pageCount = { workMenu.size })
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = Brush.verticalGradient(gradientColors))
                .statusBarsPadding()
        ) {
            SearchView(
                modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp)

            )
            Spacer(modifier = Modifier.height(8.dp))
            if (workMenu.isNotEmpty()) {
                SecondaryScrollableTabRow(
                    edgePadding = 0.dp,
                    selectedTabIndex = pagerState.currentPage,
                    containerColor = Color.Transparent,
                    indicator = {
                        Box(
                            modifier = Modifier
                                .tabIndicatorOffset(
                                    selectedTabIndex = pagerState.currentPage,
                                    matchContentSize = true
                                )
                                .height(4.dp)
                                .background(color = Color1873D4, shape = RoundedCornerShape(4.dp))
                        )
                    }) {
                    workMenu.forEachIndexed { index, title ->
                        Tab(selected = pagerState.currentPage == index, onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }, text = {
                            Text(
                                title.menuName ?: "",
                                fontSize = 14.sp,
                                color = if (pagerState.currentPage == index) Color333333 else Color666666
                            )
                        })
                    }
                }
            }
        }
        HorizontalPager(
            state = pagerState,
            key = { it },
            modifier = Modifier
                .fillMaxSize()
                .background(color = ColorF2F5F9),
            verticalAlignment = Alignment.Top
        ) { page ->
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(items = workMenu[page].subMenuList!!, key = { it.menuName ?: "" }) {
                    Items(it)
                }
            }
        }
    }
}

@Composable
fun Items(data: WorkMenuEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .padding(top = 12.dp, bottom = 12.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = data.menuName ?: "",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            maxItemsInEachRow = 4,
            horizontalArrangement = Arrangement.Start,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            data.subMenuList?.forEach {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth().height(30.dp),
                        model = it.menuIcon ?: "",
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = it.menuName ?: "",
                        autoSize = TextAutoSize.StepBased(
                            minFontSize = 10.sp,
                            maxFontSize = 14.sp,
                            stepSize = 1.sp
                        ),
                        color = Color.DarkGray
                    )
                }
            }
            // 补位逻辑：如果最后一排不满 4 个，需要添加空的 Spacer 占位，保持宽度对齐
            val emptyCount = 4 - (data.subMenuList?.size ?: 0) % 4
            if (emptyCount < 4) {
                repeat(emptyCount) { Spacer(Modifier.weight(1f)) }
            }
        }
    }

}

@Composable
fun SearchView(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(38.dp)
            .background(
                color = Color.White, shape = RoundedCornerShape(30.dp)
            )
            .border(
                width = 0.5.dp, color = Color801873d4, shape = RoundedCornerShape(30.dp)
            ), verticalAlignment = Alignment.CenterVertically
    ) {
        MImage(
            modifier = Modifier.padding(start = 16.dp),
            painter = painterResource(Res.drawable.ic_search)
        )
        TextInputField(
            modifier = Modifier.weight(1f),
            value = "",
            onValueChange = {},
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            contentPadding = contentPaddingWithLabel(
                start = 8.dp, end = 8.dp
            )
        )

        Box(
            modifier = Modifier
                .clickable {}
                .size(width = 64.dp, height = 34.dp)
                .padding(end = 2.dp)
                .background(
                    brush = Brush.horizontalGradient(colors = gradientButtonColors),
                    shape = RoundedCornerShape(30.dp)
                ), contentAlignment = Alignment.Center) {
            Text(
                text = "搜索", color = Color.White
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun WorkScreenPreview() {
//    WorkScreen()
}