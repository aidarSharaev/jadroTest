package ru.aidar.common.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import javax.inject.Inject

abstract class BaseFragment<T : BaseViewModel> : Fragment() {

    @Inject
    protected open lateinit var viewModel: T

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        inject()
    }

    abstract fun inject()
}