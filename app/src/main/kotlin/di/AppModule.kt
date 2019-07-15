package di

import android.app.Application
import android.content.Context
import core.provider.resource.ResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideResourceProvides(context: Context) = ResourceProvider(context)
}
