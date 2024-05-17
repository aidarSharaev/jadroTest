package ru.aidar.jadrotest.main

import androidx.appcompat.app.AppCompatActivity
import dagger.BindsInstance
import dagger.Component
import ru.aidar.common.di.scope.ApplicationScope
import ru.aidar.jadrotest.JadroMainActivity

@Component(
    dependencies = [MainDependencies::class],
    modules = [MainModule::class],
)
@ApplicationScope
interface MainComponent {
    companion object {
        fun init(
            activity: AppCompatActivity,
            deps: MainDependencies,
        ): MainComponent {
            return DaggerMainComponent.factory().create(activity, deps)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance activity: AppCompatActivity,
            deps: MainDependencies,
        ): MainComponent
    }

    fun inject(mainActivity: JadroMainActivity)
}