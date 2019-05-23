package com.example.androidroomkotlin.db

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class UserRepository(application: Application) {


    private val myDb = MyDatabase.getDatabase(application)
    private val userDao = myDb!!.userDao
    private var allUsers: LiveData<List<User>>? = userDao.getAllUsers()


    fun insert(vararg user: User){
        InsertAsyncTask(userDao).execute(user[0])
    }

    fun update(user: User){
        UpdateAsyncTask(userDao).execute(user)
    }
    fun delete(id: Long){
        DeleteAsyncTask(userDao).execute(id)
    }

    fun getUserById(id: Long): User{
        return UserByIdAsyncTask(userDao).execute(id).get()
    }

    fun getAllUsers(): LiveData<List<User>>? {
        return allUsers
    }


    class InsertAsyncTask(private val userDao: UserDao) : AsyncTask<User, Void, Void>() {

        override fun doInBackground(vararg users: User?): Void? {
            userDao.insertUser(users[0]!!)
            return null
        }

    }

    class UpdateAsyncTask(private val userDao: UserDao) : AsyncTask<User, Void, Void>() {

        override fun doInBackground(vararg users: User?): Void? {
            userDao.update(users[0]!!)
            return null
        }

    }

    class DeleteAsyncTask(private val userDao: UserDao) : AsyncTask<Long, Void, Void>() {

        override fun doInBackground(vararg id: Long?): Void? {
            userDao.deleteUser(id[0]!!)
            return null
        }
    }

    class UserByIdAsyncTask(private val userDao: UserDao) : AsyncTask<Long, Void, User>() {

        override fun doInBackground(vararg id: Long?): User? {
            return userDao.getUserById(id[0]!!)
        }
    }

}