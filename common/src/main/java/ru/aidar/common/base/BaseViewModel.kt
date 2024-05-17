package ru.aidar.common.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

abstract class BaseViewModel : ViewModel() {

    protected open val mainJob = SupervisorJob()

    override fun onCleared() {
        mainJob.cancelChildren()
        mainJob.cancel()
        super.onCleared()
    }
}