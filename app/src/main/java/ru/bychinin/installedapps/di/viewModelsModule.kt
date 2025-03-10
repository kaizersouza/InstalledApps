package ru.bychinin.installedapps.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.bychinin.installedapps.presentation.main.MainViewModel
import ru.bychinin.installedapps.presentation.screens.appInfo.AppInfoViewModel
import ru.bychinin.installedapps.presentation.screens.appList.AppListViewModel

val viewModelsModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::AppListViewModel)
    viewModelOf(::AppInfoViewModel)
}