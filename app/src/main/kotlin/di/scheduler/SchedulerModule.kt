package di.scheduler

import com.schibsted.android.core.scheduler.Scheduler
import core.scheduler.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class SchedulerModule {

    @Provides
    fun provideScheduler(): Scheduler = SchedulerProvider()
}
