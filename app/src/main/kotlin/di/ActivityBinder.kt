package di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import di.scopes.LiveActivity
import roommigrations.MainActivity

@Module
abstract class ActivityBinder {

    @LiveActivity
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}
