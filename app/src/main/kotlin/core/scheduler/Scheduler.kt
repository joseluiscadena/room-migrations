package com.schibsted.android.core.scheduler

import io.reactivex.Scheduler

interface Scheduler {
    fun io(): Scheduler
    fun computation(): Scheduler
    fun newThread(): Scheduler
    fun ui(): Scheduler
}
