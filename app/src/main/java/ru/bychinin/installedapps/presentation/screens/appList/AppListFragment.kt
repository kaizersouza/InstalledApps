package ru.bychinin.installedapps.presentation.screens.appList

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bychinin.installedapps.databinding.FragmentAppListBinding
import ru.bychinin.installedapps.domain.apps.App
import ru.bychinin.installedapps.domain.apps.AppErrorException
import ru.bychinin.installedapps.domain.apps.NoAppFoundException
import ru.bychinin.installedapps.presentation.common.BaseFragment
import ru.bychinin.installedapps.presentation.main.MainViewModel

class AppListFragment : BaseFragment<FragmentAppListBinding>(
    bind = FragmentAppListBinding::inflate
), AppClickListener {

    private val mainViewModel by activityViewModel<MainViewModel>()
    private val appListViewModel by viewModel<AppListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        getAppList()
    }

    private fun initObservers() {
        appListViewModel.appListState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.Loading -> setLoadingState()
                is ViewState.Error -> setErrorState(state.exception)
                is ViewState.Success<List<App>> -> setSuccessState(state.item)
            }
        }
    }

    private fun getAppList() {
        lifecycleScope.launch {
            appListViewModel.getAppList()
        }
    }

    private fun setLoadingState() {
        with(requireBinding) {
            loading.isVisible = true
        }
    }

    private fun setErrorState(exception: Exception) {
        with(requireBinding) {
            loading.isVisible = false
            when (exception) {
                is NoAppFoundException -> empty.isVisible = true
                is AppErrorException -> error.isVisible = true
            }
        }
    }

    private fun setSuccessState(appsList: List<App>) {
        with(requireBinding) {
            loading.isVisible = false
            empty.isVisible = false
            error.isVisible = false
            list.isVisible = true
            list.hasFixedSize()
            list.adapter = AppAdapter(appsList, this@AppListFragment)
        }
    }

    override fun onAppClick(app: App) {
        mainViewModel.openApp(app)
    }
}