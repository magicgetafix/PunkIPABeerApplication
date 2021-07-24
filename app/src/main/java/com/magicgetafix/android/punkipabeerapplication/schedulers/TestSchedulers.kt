package com.moneypenny.telephoneanswering.schedulers

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers


class TestSchedulers: ISchedulers {

    override val background: Scheduler
        get() = Schedulers.trampoline()
    override val ui: Scheduler
        get() = Schedulers.trampoline()
}