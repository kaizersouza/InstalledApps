package ru.bychinin.installedapps.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bychinin.installedapps.R
import ru.bychinin.installedapps.domain.apps.App
import ru.bychinin.installedapps.navigation.Navigation
import ru.bychinin.installedapps.presentation.screens.appInfo.AppInfoFragment
import ru.bychinin.installedapps.presentation.screens.appList.AppListFragment

class MainActivity : FragmentActivity(), Navigation {

    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initObservers()

    }

    private fun initObservers() {
        with(mainViewModel) {
            openAppsList.observe(this@MainActivity) { isOpen ->
                if (isOpen) openAppsList()
            }
            openApp.observe(this@MainActivity) { app ->
                open(app)
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun openAppsList() {
        showFragment(AppListFragment())
    }

    override fun open(app: App) {
        showFragment(AppInfoFragment.newInstance(app))
    }
}