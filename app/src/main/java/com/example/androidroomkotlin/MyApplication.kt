package com.example.androidroomkotlin

import android.app.Application
import com.example.androidroomkotlin.db.UserRepository

class MyApplication: Application(){

    override fun onCreate() {
        super.onCreate()
        UserRepository(this).getAllUsers()
    }
}