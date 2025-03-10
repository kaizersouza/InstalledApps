package ru.bychinin.installedapps.presentation.screens.appInfo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bychinin.installedapps.R
import ru.bychinin.installedapps.databinding.FragmentAppInfoBinding
import ru.bychinin.installedapps.domain.apps.App
import ru.bychinin.installedapps.presentation.common.BaseFragment

class AppInfoFragment : BaseFragment<FragmentAppInfoBinding>(
    bind = FragmentAppInfoBinding::inflate
) {

    private val appInfoViewModel by viewModel<AppInfoViewModel>()

    private val app: App? by lazy {
        requireArguments().getParcelable(OPEN_APP_NAME) as? App
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        fillAppData(app)
    }

    private fun initListeners() {
        requireBinding.openApp.setOnClickListener {
            openApp(app)
        }
    }

    private fun fillAppData(app: App?) {
        app?.let {
            val md5 = appInfoViewModel.md5(it)
            with(requireBinding) {
                appName.text = "${getString(R.string.apps_details_app_name)} ${app.appName}"
                appVersion.text =
                    "${getString(R.string.apps_details_app_version)} ${app.appVersion}"
                appPackageName.text =
                    "${getString(R.string.apps_details_package_name)} ${app.packageName}"
                appMd5.text = "${getString(R.string.apps_details_package_md5)} $md5"
            }
        }
    }

    private fun openApp(app: App?) {
        try {
            app?.packageName?.let {
                requireContext().packageManager.getLaunchIntentForPackage(it).let { intent ->
                    startActivity(intent!!)
                }
            }
        } catch (_: Exception) {
            showError()
        }
    }

    private fun showError() {
        Toast.makeText(
            requireContext(),
            getString(R.string.apps_details_open_app_error),
            Toast.LENGTH_SHORT
        ).show()
    }

    companion object {

        private const val OPEN_APP_NAME = "OPEN_APP_NAME"

        fun newInstance(app: App): AppInfoFragment {
            return AppInfoFragment().apply {
                arguments = bundleOf(
                    OPEN_APP_NAME to app
                )
            }
        }
    }
}