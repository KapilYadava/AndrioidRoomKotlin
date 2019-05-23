package com.example.androidroomkotlin.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface UserDao{

    @Insert
    fun insertUser(vararg  user: User)

    @Query("SELECT * FROM user")
    fun getAllUsers(): LiveData<List<User>>

    @Query("DELETE FROM user WHERE id = :id")
    fun deleteUser(id : Long)

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: Long): User

    @Update
    fun update(vararg user: User)
}