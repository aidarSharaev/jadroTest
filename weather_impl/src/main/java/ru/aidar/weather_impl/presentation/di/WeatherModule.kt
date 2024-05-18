package ru.aidar.weather_impl.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.aidar.common.core.resources.JadroResourceManager
import ru.aidar.common.di.viewmodel.ViewModelKey
import ru.aidar.common.di.viewmodel.ViewModelModule
import ru.aidar.weather_api.repo.WeatherUseCases
import ru.aidar.weather_api.wrap.WeatherStateWrapper
import ru.aidar.weather_impl.WeatherRouter
import ru.aidar.weather_impl.presentation.WeatherViewModel

@Module(includes = [ViewModelModule::class])
class WeatherModule {

    @Provides
    fun provideViewModelProvider(
        fragment: Fragment,
        factory: ViewModelProvider.Factory,
    ): WeatherViewModel {
        return ViewModelProvider(fragment, factory)[WeatherViewModel::class.java]
    }

    @Provides
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    fun provideViewModel(
        router: WeatherRouter,
        useCases: WeatherUseCases,
        wrapper: WeatherStateWrapper,
        resourceManager: JadroResourceManager,
    ): ViewModel {
        return WeatherViewModel(
            router = router,
            useCases = useCases,
            wrapper = wrapper,
            resourceManager = resourceManager
        )
    }
}