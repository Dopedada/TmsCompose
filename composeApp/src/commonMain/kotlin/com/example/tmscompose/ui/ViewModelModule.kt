package com.example.tmscompose.ui

import com.example.tmscompose.ui.screen.home.HomeScreenViewModel
import com.example.tmscompose.ui.screen.login.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { HomeScreenViewModel(get()) }
}