package com.example.androidroomkotlin.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user")
data class User (

    @ColumnInfo(name = "first_name")
    var firstName:String?,
    @ColumnInfo(name = "last_name")
    var lastName:String?
){
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
}