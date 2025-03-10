package ru.bychinin.installedapps.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding>(
    private val bind: (LayoutInflater, ViewGroup?, Boolean) -> T,
    private val attachToParent: Boolean = false,
) : Fragment() {

    private var _binding: T? = null
    protected val requireBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = bind(inflater, container, attachToParent)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    sealed class ViewState<out T> {
        object Loading : ViewState<Nothing>()
        data class Success<T>(val item: T) : ViewState<T>()
        data class Error(val exception: Exception) : ViewState<Nothing>()
    }
}