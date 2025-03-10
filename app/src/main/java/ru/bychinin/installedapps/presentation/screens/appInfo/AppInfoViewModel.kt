package ru.bychinin.installedapps.presentation.screens.appInfo

import androidx.lifecycle.ViewModel
import ru.bychinin.installedapps.domain.apps.App

class AppInfoViewModel : ViewModel() {

    @OptIn(ExperimentalStdlibApi::class)
    fun md5(app: App): String {
        val md5 = app.md5()
        return md5
    }
}