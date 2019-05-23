package com.example.androidroomkotlin.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import android.util.Log

@Database(entities = arrayOf(User::class), version = 1)
 abstract class MyDatabase: RoomDatabase(){
    abstract val userDao: UserDao

    companion object {
        private var INSTANCE: MyDatabase? = null
        fun getDatabase(context: Context): MyDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, MyDatabase::class.java, "my-db")
                    .fallbackToDestructiveMigration().addCallback(roomCallback)
                    .build()
            }
            return INSTANCE
        }


        private val roomCallback = object : RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateAsyncTask(INSTANCE!!).execute()
                Log.v("callback", "room callback executed!")

            }
        }
    }

    class PopulateAsyncTask(private val myDatabase: MyDatabase) : AsyncTask<User, Void, Void>() {


        override fun doInBackground(vararg params: User?): Void? {

            val user1 = User("Kapil", "Kumar")
            val user2 = User("Amit", "Singh")
            val user3 = User("Shankar", "Reddy")

            myDatabase.userDao.insertUser(user1, user2, user3)

            Log.v("insert", "Data Inserted!")

            return null

        }
    }

}