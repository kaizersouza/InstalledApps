package ru.bychinin.installedapps.presentation.screens.appList

import ru.bychinin.installedapps.domain.apps.App

interface AppClickListener {
    fun onAppClick(app: App)
}