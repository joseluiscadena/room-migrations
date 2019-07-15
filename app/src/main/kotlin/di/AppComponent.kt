package di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import di.scheduler.SchedulerModule
import di.user.UserModule
import di.viewmodel.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBinder::class,
        AppModule::class,
        ViewModelModule::class,
        SchedulerModule::class,
        UserModule::class]
)

interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
