package com.example.androidroomkotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.androidroomkotlin.db.User
import kotlinx.android.synthetic.main.fragment_add_user.view.*
import kotlinx.android.synthetic.main.list_item.view.*

class MyRecyclerViewAdapter(private val context: Context, private val list: List<User>): RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>(){


    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val userName: TextView = view.userName
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.userName.text = list[position].firstName
    }

    override fun getItemCount(): Int {
        return list.size
    }
}