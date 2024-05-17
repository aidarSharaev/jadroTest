package ru.aidar.jadrotest.main

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.aidar.common.di.viewmodel.ViewModelKey
import ru.aidar.common.di.viewmodel.ViewModelModule
import ru.aidar.jadrotest.JadroViewModel

@Module(includes = [ViewModelModule::class])
class MainModule {

    @Provides
    @IntoMap
    @ViewModelKey(JadroViewModel::class)
    fun provideViewModel(): ViewModel {
        return JadroViewModel()
    }

    @Provides
    fun provideViewModelProvider(
        activity: AppCompatActivity,
        viewModelFactory: ViewModelProvider.Factory,
    ): JadroViewModel {
        return ViewModelProvider(activity, viewModelFactory)[JadroViewModel::class.java]
    }
}