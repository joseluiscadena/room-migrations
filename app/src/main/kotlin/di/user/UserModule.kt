package di.user

import android.content.Context
import com.schibsted.android.core.scheduler.Scheduler
import dagger.Module
import dagger.Provides
import user.data.datasource.room.UserDao
import user.data.datasource.room.UserRoomDataSource
import user.data.datasource.room.UserRoomDatabase
import user.data.repository.UserRepository
import user.domain.InsertUserUseCase
import javax.inject.Singleton

@Module
class UserModule {

    @Singleton
    @Provides
    fun provideUserRoomDatabase(context: Context) = UserRoomDatabase.build(context)

    @Singleton
    @Provides
    fun provideUserDao(userRoomDatabase: UserRoomDatabase) = userRoomDatabase.userDao()

    @Singleton
    @Provides
    fun provideUserRoomDataSource(userDao: UserDao) = UserRoomDataSource(userDao)

    @Singleton
    @Provides
    fun provideUserRepository(userRoomDataSource: UserRoomDataSource) =
        UserRepository(userRoomDataSource)

    @Provides
    fun provideInsertUserUseCase(userRepository: UserRepository, scheduler: Scheduler) =
        InsertUserUseCase(userRepository, scheduler)
}
