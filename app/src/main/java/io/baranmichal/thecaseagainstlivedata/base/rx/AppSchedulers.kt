package io.baranmichal.thecaseagainstlivedata.base.rx

import io.reactivex.Scheduler

class AppSchedulers(
    val main: Scheduler,
    val computation: Scheduler,
    val io: Scheduler)