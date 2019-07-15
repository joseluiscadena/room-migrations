package user.data.datasource.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import user.data.datasource.room.UserConstants.DATABASE_USER
import user.data.datasource.room.UserConstants.DATABASE_USER_VERSION

@Database(entities = [(UserEntity::class)], version = DATABASE_USER_VERSION , exportSchema = false)
abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        fun build(context: Context) = Room.databaseBuilder(context, UserRoomDatabase::class.java, DATABASE_USER)
            .build()
    }
}
