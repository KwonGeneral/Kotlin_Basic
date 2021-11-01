package com.example.kotlin_basic.User

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_basic.R
import com.example.kotlin_basic.User.contain.Define.Companion.USER_ADDRESS
import com.example.kotlin_basic.User.contain.Define.Companion.USER_AGE
import com.example.kotlin_basic.User.contain.Define.Companion.USER_CREATE_SUCCESS
import com.example.kotlin_basic.User.contain.Define.Companion.USER_GENDER
import com.example.kotlin_basic.User.contain.Define.Companion.USER_NAME
import com.example.kotlin_basic.User.contain.Define.Companion.USER_PHONE
import com.example.kotlin_basic.User.contain.Define.Companion.pleasePrint
import com.example.kotlin_basic.User.model.UserData
import com.example.kotlin_basic.User.model.UserDataBase
import kotlinx.android.synthetic.main.fragment_user_create.*


class UserCreateFragment : Fragment() {

    companion object{
        fun newInstance() : UserCreateFragment {
            return UserCreateFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user_create, container, false)
        Log.d("TEST","UserCreateFragment - onCreateView")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TEST","UserCreateFragment - onViewCreated")

        create_submit_btn.setOnClickListener {
            fun checkEditText(text:String, tag:String): Boolean {
                return when(text.isEmpty()) {
                    true -> {
                        create_status_message.setTextColor(Color.RED)
                        create_status_message.text = pleasePrint(tag)
                        true
                    }
                    false -> false
                }
            }
            if(checkEditText(create_user_name.text.toString(), USER_NAME)) return@setOnClickListener
            if(checkEditText(create_user_age.text.toString(), USER_AGE)) return@setOnClickListener
            if(checkEditText(create_user_gender.text.toString(), USER_GENDER)) return@setOnClickListener
            if(checkEditText(create_user_address.text.toString(), USER_ADDRESS)) return@setOnClickListener
            if(checkEditText(create_user_phone.text.toString(), USER_PHONE)) return@setOnClickListener

            UserDataBase.getInstance(requireContext())?.userDao()?.userClear()
            UserDataBase.getInstance(requireContext())?.userDao()?.userCreate(
                UserData(
                    id = null,
                    name = create_user_name.text.toString(),
                    age = create_user_age.text.toString(),
                    gender = create_user_gender.text.toString(),
                    address = create_user_address.text.toString(),
                    phone = create_user_phone.text.toString()
                )
            )

            create_status_message.setTextColor(Color.GREEN)
            create_status_message.text = USER_CREATE_SUCCESS

            Log.d("TEST", "userRead : ${UserDataBase.getInstance(requireContext())?.userDao()?.userRead()}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TEST","UserCreateFragment - onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("TEST","UserCreateFragment - onAttach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TEST", "UserCreateFragment - onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TEST", "UserCreateFragment - onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TEST", "UserCreateFragment - onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TEST", "UserCreateFragment - onDestroy")
    }
}