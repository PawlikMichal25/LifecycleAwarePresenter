package io.baranmichal.thecaseagainstlivedata.base.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.baranmichal.thecaseagainstlivedata.base.di.qualifiers.ApplicationContext
import io.baranmichal.thecaseagainstlivedata.base.di.scopes.PerApp
import io.baranmichal.thecaseagainstlivedata.base.rx.AppSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context = application

    @Provides
    @PerApp
    fun provideAppSchedulers(): AppSchedulers =
        AppSchedulers(AndroidSchedulers.mainThread(), Schedulers.computation(), Schedulers.io())
}