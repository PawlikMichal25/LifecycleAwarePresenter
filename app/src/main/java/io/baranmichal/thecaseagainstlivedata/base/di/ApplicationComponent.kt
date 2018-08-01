package io.baranmichal.thecaseagainstlivedata.base.di

import dagger.Component
import io.baranmichal.thecaseagainstlivedata.LifeApplication
import io.baranmichal.thecaseagainstlivedata.base.di.scopes.PerApp

@PerApp
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {
    fun inject(application: LifeApplication)
}