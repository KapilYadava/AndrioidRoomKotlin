package com.example.androidroomkotlin

import android.content.Context
import android.widget.Toast

class Utilities{

    fun showToast(context: Context, msg: String){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }
}