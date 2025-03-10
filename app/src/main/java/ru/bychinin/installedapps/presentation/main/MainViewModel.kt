package ru.bychinin.installedapps.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.bychinin.installedapps.domain.apps.App

class MainViewModel : ViewModel() {

    private val _openAppsList = MutableLiveData<Boolean>()
    val openAppsList: LiveData<Boolean> get() = _openAppsList

    private val _openApp = MutableLiveData<App>()
    val openApp: LiveData<App> get() = _openApp

    init {
        _openAppsList.value = true
    }

    fun openApp(app: App) {
        _openApp.value = app
    }
}