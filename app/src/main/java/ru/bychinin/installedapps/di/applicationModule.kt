package ru.bychinin.installedapps.di

import android.content.Context
import android.content.pm.PackageManager
import org.koin.dsl.module

val applicationModule = module {

    factory<PackageManager> {
        get<Context>().packageManager
    }

}