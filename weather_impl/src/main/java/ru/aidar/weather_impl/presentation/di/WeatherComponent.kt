package ru.aidar.weather_impl.presentation.di

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Subcomponent
import ru.aidar.common.di.scope.weather.WeatherScreenScope
import ru.aidar.weather_impl.presentation.WeatherFragment

@WeatherScreenScope
@Subcomponent(modules = [WeatherModule::class])
interface WeatherComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment,
        ): WeatherComponent
    }

    fun inject(fragment: WeatherFragment)
}
