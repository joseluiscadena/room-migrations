package di

import android.app.Activity
import androidx.multidex.MultiDexApplication
import com.roommigrations.BuildConfig
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class App : MultiDexApplication(), HasActivityInjector {

    @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initDagger()
    }

    override fun activityInjector() = dispatchingActivityInjector

    private fun initTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    private fun initDagger() {
        DaggerAppComponent.builder().application(this).build().inject(this)
    }
}
