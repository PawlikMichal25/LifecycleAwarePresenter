package io.baranmichal.thecaseagainstlivedata.base.rx

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

object AppSchedulersTest {

    fun testSchedulers(testScheduler: Scheduler): AppSchedulers {
        return AppSchedulers(testScheduler, testScheduler, testScheduler)
    }

    fun immediateSchedulers(): AppSchedulers {
        return testSchedulers(Schedulers.trampoline())
    }
}