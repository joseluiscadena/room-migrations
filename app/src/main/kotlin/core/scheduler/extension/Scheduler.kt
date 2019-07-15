package com.schibsted.android.core.scheduler.extension

import com.schibsted.android.core.scheduler.Scheduler
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

fun <T> Observable<T>.runOnIo(scheduler: Scheduler): Observable<T> =
        subscribeOn(scheduler.io()).observeOn(scheduler.ui())

fun <T> Single<T>.runOnIo(scheduler: Scheduler): Single<T> =
        subscribeOn(scheduler.io()).observeOn(scheduler.ui())

fun <T> Flowable<T>.runOnIo(scheduler: Scheduler): Flowable<T> =
        subscribeOn(scheduler.io()).observeOn(scheduler.ui())

fun Completable.runOnIo(scheduler: Scheduler): Completable =
        subscribeOn(scheduler.io()).observeOn(scheduler.ui())
