package ru.bychinin.installedapps.presentation.screens.appList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.bychinin.installedapps.domain.apps.App
import ru.bychinin.installedapps.domain.apps.AppErrorException
import ru.bychinin.installedapps.domain.apps.AppRepository
import ru.bychinin.installedapps.domain.apps.NoAppFoundException
import ru.bychinin.installedapps.presentation.common.BaseFragment.ViewState

class AppListViewModel(
    private val appRepository: AppRepository
): ViewModel() {

    private val _appListState = MutableLiveData<ViewState<List<App>>>()
    val appListState: LiveData<ViewState<List<App>>> get() = _appListState

    suspend fun getAppList() {
        _appListState.postValue(ViewState.Loading)
        try {
            val appsList = appRepository.getAppsList()
            _appListState.postValue(ViewState.Success(appsList))
        }
        catch (exception: Exception) {
            _appListState.postValue(
                ViewState.Error(
                    if (exception is NoAppFoundException) {
                        NoAppFoundException()
                    } else {
                        AppErrorException()
                    }
                )
            )
        }
    }
}