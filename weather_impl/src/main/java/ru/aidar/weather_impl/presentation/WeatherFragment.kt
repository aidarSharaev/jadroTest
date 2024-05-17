package ru.aidar.weather_impl.presentation

import ru.aidar.common.base.BaseFragment
import ru.aidar.common.di.FeatureUtils
import ru.aidar.weather_api.di.WeatherFeatureApi
import ru.aidar.weather_impl.databinding.WeatherFragmentBinding
import ru.aidar.weather_impl.di.WeatherFeatureComponent

class WeatherFragment() : BaseFragment<WeatherViewModel>() {

    lateinit var binding: WeatherFragmentBinding

    override fun inject() {
        FeatureUtils.getFeature<WeatherFeatureComponent>(this, WeatherFeatureApi::class.java)
            .weatherLoginComponentFactory()
            .create(this)
            .inject(this)
    }


}