package com.example.androidroomkotlin

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemLongClickListener
import com.example.androidroomkotlin.Model.UserViewModel
import com.example.androidroomkotlin.db.User
import kotlinx.android.synthetic.main.fragment_list.view.*

class UserList : ListFragment(), OnClickListener, OnItemLongClickListener, AdapterView.OnItemClickListener{

    var userList: List<User>? = null
    private var userModel: UserViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userModel!!.getAllUsers()!!.observe(this, Observer<List<User>> {
            userList = it
            Utilities().showToast(activity!!.applicationContext, userList!!.size.toString())
            listAdapter = MyListAdapter(this.requireActivity(), userList!!)
        })
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_list, container, false)
        v.floatingActionButton.setOnClickListener(this)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        listView.onItemLongClickListener = this
        listView.onItemClickListener = this
    }

    override fun onClick(v: View?) {
        activity!!.supportFragmentManager.beginTransaction().replace(R.id.container, AddUser()).commit()
    }

    override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {
        userModel!!.delete(userList!![position].id)
        Utilities().showToast(activity!!.applicationContext, "User Deleted!")
        return true
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val bundle = Bundle().apply { putLong("ID", userList!![position].id) }
        val addUser = AddUser().apply{ arguments = bundle }
        activity!!.supportFragmentManager.beginTransaction().replace(R.id.container, addUser).commit()
    }
}