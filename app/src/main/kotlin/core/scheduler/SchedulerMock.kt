package com.schibsted.android.core.scheduler

import io.reactivex.schedulers.Schedulers

class SchedulerMock : Scheduler {

    override fun io() = Schedulers.trampoline()

    override fun computation() = Schedulers.trampoline()

    override fun newThread() = Schedulers.trampoline()

    override fun ui() = Schedulers.trampoline()
}
