package ru.aidar.jadrotest.app

import com.example.horizonGuard.di.deps.ComponentHolderModule
import dagger.BindsInstance
import dagger.Component
import ru.aidar.common.di.CommonApi
import ru.aidar.common.di.modules.CommonModule
import ru.aidar.common.di.modules.NetworkModule
import ru.aidar.common.di.scope.ApplicationScope
import ru.aidar.jadrotest.JadroApplication
import ru.aidar.jadrotest.deps.ComponentDependenciesModule
import ru.aidar.jadrotest.main.MainDependencies

@Component(
    modules = [
        AppModule::class,
        CommonModule::class,
        NetworkModule::class,
        NavigationModule::class,
        FeatureManagerModule::class,
        ComponentDependenciesModule::class,
        ComponentHolderModule::class
    ]
)
@ApplicationScope
interface AppComponent : MainDependencies, CommonApi {

    companion object {

        fun init(application: JadroApplication): AppComponent {
            return DaggerAppComponent
                .builder()
                .application(application)
                .build()
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: JadroApplication): Builder

        fun build(): AppComponent
    }

    fun inject(application: JadroApplication)
}