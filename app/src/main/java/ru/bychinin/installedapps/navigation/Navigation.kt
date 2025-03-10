package ru.bychinin.installedapps.navigation

import ru.bychinin.installedapps.domain.apps.App

interface Navigation {
    fun openAppsList()
    fun open(app: App)
}