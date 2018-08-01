package io.baranmichal.thecaseagainstlivedata.base.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.baranmichal.thecaseagainstlivedata.base.di.qualifiers.ApplicationContext

@Module
class ApplicationModule (private val application: Application) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context = application
}