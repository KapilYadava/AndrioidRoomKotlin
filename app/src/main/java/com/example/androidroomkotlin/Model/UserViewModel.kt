package com.example.androidroomkotlin.Model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.androidroomkotlin.db.User
import com.example.androidroomkotlin.db.UserRepository
import java.util.concurrent.LinkedBlockingDeque

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private var userRepository: UserRepository = UserRepository(application)
    private var allUsers: LiveData<List<User>> ? = userRepository.getAllUsers()


    fun update(user: User){
        userRepository.update(user)
    }

    fun delete(id: Long){
        userRepository.delete(id)
    }

    fun getUserById(id: Long): User{
        return userRepository.getUserById(id)
    }

    fun insert(user: User){
        userRepository.insert(user)
    }

    fun getAllUsers(): LiveData<List<User>>?{
        return allUsers
    }
}