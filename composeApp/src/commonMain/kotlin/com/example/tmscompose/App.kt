package com.example.tmscompose

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tmscompose.commom.SettingsUtils
import com.example.tmscompose.network.clientModule
import com.example.tmscompose.network.repositoryModule
import com.example.tmscompose.ui.screen.SplashScreen
import com.example.tmscompose.ui.screen.login.LoginScreen
import com.example.tmscompose.ui.viewModelModule
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.KoinApplication

const val IS_LOGIN = "isLogin"

fun appModule() = listOf(clientModule, repositoryModule, viewModelModule)

@Composable
@Preview
fun App() {
    KoinApplication(application = { modules(appModule()) }) {
        MaterialTheme {
            val navController = rememberNavController()
            val coroutineScope = rememberCoroutineScope()
            coroutineScope.launch {
                delay(2000)
                val route = if (SettingsUtils.getBoolean(IS_LOGIN)) "" else "Login"
                navController.navigate(route) {
                    popUpTo("Splash") {
                        inclusive = true
                    }
                }
            }

            NavHost(navController = navController, startDestination = "Splash") {
                composable("Splash") {
                    SplashScreen()
                }
                composable("Login") {
                    LoginScreen(navController = navController)
                }
            }
        }
    }
}
