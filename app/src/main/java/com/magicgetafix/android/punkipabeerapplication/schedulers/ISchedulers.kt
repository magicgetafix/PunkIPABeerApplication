package com.moneypenny.telephoneanswering.schedulers

import io.reactivex.rxjava3.core.Scheduler


interface ISchedulers {

    val background: Scheduler
    val ui: Scheduler
}