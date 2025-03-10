package ru.bychinin.installedapps.domain.apps

interface AppRepository {
    suspend fun getAppsList(): List<App>
}