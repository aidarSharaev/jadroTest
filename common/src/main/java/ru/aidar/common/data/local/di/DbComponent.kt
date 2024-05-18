package ru.aidar.common.data.local.di

import dagger.Component
import ru.aidar.common.di.CommonApi
import ru.aidar.common.di.scope.ApplicationScope

@Component(
    modules = [DbModule::class],
    dependencies = [DbDependencies::class],
)
@ApplicationScope
abstract class DbComponent : DbApi {
    @Component(dependencies = [CommonApi::class])
    interface DbDependenciesComponent : DbDependencies
}