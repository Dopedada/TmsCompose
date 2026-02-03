package com.example.tmscompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tmscompose.commom.getStoreBoolean
import com.example.tmscompose.network.clientModule
import com.example.tmscompose.network.repositoryModule
import com.example.tmscompose.ui.dialog.GlobalDialog
import com.example.tmscompose.ui.dialog.GlobalManager
import com.example.tmscompose.ui.dialog.LocalGlobalDialogManager
import com.example.tmscompose.ui.screen.SplashScreen
import com.example.tmscompose.ui.screen.home.HomeScreen
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
        val dialogManager = remember { GlobalManager() }
        CompositionLocalProvider(LocalGlobalDialogManager provides dialogManager) {
            MaterialTheme {
                val navController = rememberNavController()
                val coroutineScope = rememberCoroutineScope()
                coroutineScope.launch {
                    delay(2000)
                    val route = if (getStoreBoolean(IS_LOGIN)) "Home" else "Login"
                    navController.navigate(route) {
                        popUpTo("Splash") {
                            inclusive = true
                        }
                    }
                }
                Box(modifier = Modifier.fillMaxSize()) {
                    NavHost(navController = navController, startDestination = "Splash") {
                        composable("Splash") {
                            SplashScreen()
                        }
                        composable("Login") {
                            LoginScreen(navController = navController)
                        }
                        composable("Home") {
                            HomeScreen()
                        }
                    }
                    GlobalDialog()
                }

            }
        }
    }
}
