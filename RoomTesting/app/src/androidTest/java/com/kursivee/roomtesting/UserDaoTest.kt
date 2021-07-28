package com.kursivee.roomtesting

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test naming convention follows Optional(Given)_When_Then
 */
@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    private lateinit var userDao: UserDao
    private lateinit var db: TestDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TestDatabase::class.java).build()
        userDao = db.userDao()
    }


    @Test
    fun createAndGetUsers_returnUsers() {
        val users = listOf(User(id = 1, name = "foo"), User(id = 2, "bar"))
        runBlocking {
            userDao.insertUsers(users)
            assertThat(userDao.getUsers(), equalTo(users))
        }
    }

    @Test
    fun userExists_findUserById_returnUser() {
        val user = User(id = 1, name = "foo")
        runBlocking {
            userDao.insertUser(user)
            assertThat(userDao.findUserById(1), equalTo(user))
        }
    }

    @Test
    fun userDoesNotExist_findUserById_returnNull() {
        runBlocking {
            assertThat(userDao.findUserById(1), equalTo(null))
        }
    }
}