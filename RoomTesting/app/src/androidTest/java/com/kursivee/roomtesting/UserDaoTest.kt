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
    private lateinit var addressDao: AddressDao
    private lateinit var db: TestDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TestDatabase::class.java).build()
        userDao = db.userDao()
        addressDao = db.addressDao()
    }


    @Test
    fun createAndGetUsers_returnUsers() {
        val users = listOf(User(userId = 1, name = "foo"), User(userId = 2, "bar"))
        runBlocking {
            userDao.insertUsers(users)
            assertThat(userDao.getUsers(), equalTo(users))
        }
    }

    @Test
    fun userExists_findUserById_returnUser() {
        val user = User(userId = 1, name = "foo")
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

    @Test
    fun createAndGetUsersWithAddress_returnUsersWithAddress() {
        val user = User(userId = 1, name = "foo")
        val address = Address(addressId = 1, userId = 1, address = "1234 F", city = "foo", state = "bar")
        runBlocking {
            userDao.insertUser(user)
            addressDao.insertAddress(address)
            val users = userDao.getUsersWithAddress()
            val expected = UserWithAddress(user, address)
            assertThat(users, equalTo(listOf(expected)))
        }
    }
}