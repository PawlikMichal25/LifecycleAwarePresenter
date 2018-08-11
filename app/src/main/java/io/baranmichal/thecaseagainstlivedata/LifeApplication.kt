package io.baranmichal.thecaseagainstlivedata

import android.app.Application
import io.baranmichal.thecaseagainstlivedata.base.di.ApplicationComponent
import io.baranmichal.thecaseagainstlivedata.base.di.ApplicationModule
import io.baranmichal.thecaseagainstlivedata.base.di.DaggerApplicationComponent

class LifeApplication: Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        application = this

        initDagger()
    }

    private fun initDagger() {
        applicationComponent =
                DaggerApplicationComponent
                    .builder()
                    .applicationModule(ApplicationModule(this))
                    .build()
        applicationComponent.inject(this)
    }

    fun getComponent() = applicationComponent

    companion object {
        private lateinit var application: LifeApplication

        fun get(): LifeApplication = application
    }
}