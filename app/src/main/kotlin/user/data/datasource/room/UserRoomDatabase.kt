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
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_1_3)
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

val MIGRATION_2_3: Migration = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.run {
            execSQL("CREATE TABLE user_new (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "account_id INTEGER NOT NULL, username TEXT NOT NULL, email TEXT NOT NULL, address TEXT NOT NULL," +
                    "phone TEXT NOT NULL)")
            execSQL("INSERT INTO user_new (account_id, username, email, address, phone)" +
                    "SELECT account_id, username, email, '', phone FROM user")
            execSQL("DROP TABLE user")
            execSQL("ALTER TABLE user_new RENAME TO user")
        }
    }
}

val MIGRATION_1_3: Migration = object : Migration(1, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.run {
            execSQL("CREATE TABLE user_new (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "account_id INTEGER NOT NULL, username TEXT NOT NULL, email TEXT NOT NULL, address TEXT NOT NULL," +
                    "phone TEXT NOT NULL)")
            execSQL("INSERT INTO user_new (account_id, username, email, address, phone)" +
                    "SELECT account_id, username, email, '', '' FROM user")
            execSQL("DROP TABLE user")
            execSQL("ALTER TABLE user_new RENAME TO user")
        }
    }
}
