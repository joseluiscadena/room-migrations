package com.roommigrations

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import user.data.datasource.room.MIGRATION_1_2
import user.data.datasource.room.MIGRATION_1_3
import user.data.datasource.room.MIGRATION_2_3
import user.data.datasource.room.UserEntity
import user.data.datasource.room.UserRoomDatabase

@RunWith(AndroidJUnit4::class)
class MigrationTest {

    @get:Rule
    var migrationTestHelper = MigrationTestHelper(InstrumentationRegistry.getInstrumentation(), UserRoomDatabase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory())

    companion object {
        const val TEST_DB_NAME = "user.db"
        const val ANY_ACCOUNT_ID = "227799"
        const val ANY_USER_NAME = "joseph.dev89"
        const val ANY_EMAIL = "joseph.dev89@gmail.com"
    }

    @Test
    fun onMigrationFrom1To2_CheckIf_UserTableContainsCorrectData() {
        val database: SupportSQLiteDatabase = migrationTestHelper.createDatabase(TEST_DB_NAME, 1)

        insertUser(ANY_ACCOUNT_ID, ANY_USER_NAME, ANY_EMAIL, database)

        migrationTestHelper.runMigrationsAndValidate(TEST_DB_NAME, 2, true, MIGRATION_1_2)

        val userRoomDataBase = getDatabaseAfterPerformingMigrations(migrationTestHelper, MIGRATION_1_2, MIGRATION_2_3, MIGRATION_1_3) as UserRoomDatabase

        val userDao = userRoomDataBase.userDao()
        val userEntityList: Single<List<UserEntity>> = userDao.getAll()
        val list: List<UserEntity> = userEntityList.test().assertNoErrors().values()[0]
        assertEquals(list[0].accountId, ANY_ACCOUNT_ID.toInt())
        assertEquals(list[0].address, "")
    }

    private fun insertUser(accountId: String, username: String, email: String, database: SupportSQLiteDatabase) {
        val contentValues = ContentValues()
        contentValues.put("account_id", accountId)
        contentValues.put("username", username)
        contentValues.put("email", email)

        database.insert("user", SQLiteDatabase.CONFLICT_REPLACE, contentValues)
        database.close()
    }

    private fun getDatabaseAfterPerformingMigrations(migrationTestHelper: MigrationTestHelper, vararg migrations: Migration): RoomDatabase {
        val roomDatabase =
            Room.databaseBuilder(ApplicationProvider.getApplicationContext<Context>(), UserRoomDatabase::class.java, TEST_DB_NAME)
                .addMigrations(*migrations)
                .build()
        migrationTestHelper.closeWhenFinished(roomDatabase)
        return roomDatabase
    }
}
