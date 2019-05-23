package com.example.androidroomkotlin

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import com.example.androidroomkotlin.Model.UserViewModel
import com.example.androidroomkotlin.db.User
import kotlinx.android.synthetic.main.fragment_add_user.view.*
import android.view.View as View

class AddUser: Fragment(), OnClickListener{

    private var view1: View? = null
    private var isUpdate: Boolean = false
    private var id: Long? = null
    private var model: ViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProviders.of(this).get(UserViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view1 = inflater.inflate(R.layout.fragment_add_user, container, false)
        view1!!.submitBtn.setOnClickListener(this)
        return view1
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(arguments != null) {
            id = arguments!!.getLong("ID")
            val user = UserViewModel(activity!!.application).getUserById(id!!)
            view1!!.firstName.text = Editable.Factory.getInstance().newEditable(user.firstName)
            view1!!.lastName.text = Editable.Factory.getInstance().newEditable(user.lastName)
            view1!!.submitBtn.text = "Update"
            isUpdate = true
        }

    }

    override fun onClick(v: View?) {

        if (view1!!.firstName.length() < 4 || view1!!.lastName.length() < 4){
            Toast.makeText(activity,
                "All fields are mandatory and must be greater than 4 characters!",
                Toast.LENGTH_LONG).show()
            return
        }

        val user = User(view1!!.firstName.text.toString(), view!!.lastName.text.toString())

        if (isUpdate){
            user.id = id!!
            UserViewModel(activity!!.application).update(user)
            Utilities().showToast(activity!!.applicationContext, "User Updated!")
        }else {
            UserViewModel(activity!!.application).insert(user)
            Utilities().showToast(activity!!.applicationContext, "User Added!")
        }
        view1!!.firstName.text.clear()
        view1!!.lastName.text.clear()
        this.requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container, UserList()).commit()
    }
}