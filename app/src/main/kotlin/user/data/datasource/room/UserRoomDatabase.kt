package user.data.datasource.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import user.data.datasource.room.UserConstants.DATABASE_USER
import user.data.datasource.room.UserConstants.DATABASE_USER_VERSION

@Database(entities = [(UserEntity::class)], version = DATABASE_USER_VERSION , exportSchema = true)
abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        fun build(context: Context) = Room.databaseBuilder(context, UserRoomDatabase::class.java, DATABASE_USER)
            .addMigrations(MIGRATION_1_2)
            .build()
    }
}

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.run {
            execSQL("ALTER TABLE user ADD COLUMN phone TEXT DEFAULT '' NOT NULL")
        }
    }
}
