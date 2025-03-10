package ru.bychinin.installedapps.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.bychinin.installedapps.data.apps.AppRepositoryImpl
import ru.bychinin.installedapps.domain.apps.AppRepository

val repositoriesModule = module {

    singleOf(::AppRepositoryImpl) bind AppRepository::class
}