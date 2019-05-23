package com.example.androidroomkotlin

import android.arch.lifecycle.LiveData
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.androidroomkotlin.db.User
import kotlinx.android.synthetic.main.list_item.view.*

class MyListAdapter(private val context: FragmentActivity, private val list: List<User>): BaseAdapter(){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        v.userName.text = list[position].firstName
        return  v
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()
    }
}