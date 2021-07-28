package com.kursivee.roomtesting

import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Rule
import org.junit.Test
import java.io.IOException


class MigrationTest {

    companion object {
        const val TEST_DB = "TEST_DB"
    }

    @get:Rule
    val helper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        TestDatabase::class.java,
        listOf(Migration()),
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    @Throws(IOException::class)
    fun migrateVersion1to2() {
        helper.createDatabase(TEST_DB, 1). apply {
            execSQL("INSERT INTO User VALUES ('1', 'foo')")
            close()
        }
        helper.runMigrationsAndValidate("NewUserTable", 2, true)
        runBlocking {
            assertThat(getMigratedRoomDatabase().userDao().findUserById(1), equalTo(User(1, "foo")))
        }
    }

    private fun getMigratedRoomDatabase(): TestDatabase {
        val database: TestDatabase = Room.databaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TestDatabase::class.java,
            TEST_DB
        ).build()
        // close the database and release any stream resources when the test finishes
        helper.closeWhenFinished(database)
        return database
    }
}