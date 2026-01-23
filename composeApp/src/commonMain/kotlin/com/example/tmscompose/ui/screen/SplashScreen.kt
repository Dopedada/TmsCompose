package com.example.tmscompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import tmscompose.composeapp.generated.resources.Res
import tmscompose.composeapp.generated.resources.ic_name_logo
import tmscompose.composeapp.generated.resources.ic_splash_logo
import tmscompose.composeapp.generated.resources.ic_splash_logo_bg

@Composable
fun SplashScreen() {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(130.dp))
        Image(
            painter = painterResource(Res.drawable.ic_splash_logo),
            contentDescription = ""
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            painter = painterResource(Res.drawable.ic_splash_logo_bg),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(Res.drawable.ic_name_logo),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(14.dp))
        Spacer(modifier = Modifier.height(36.dp))
    }
}