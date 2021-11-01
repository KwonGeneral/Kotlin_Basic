package com.example.kotlin_basic.User

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_basic.R
import kotlinx.android.synthetic.main.fragment_user_update.*


class UserUpdateFragment : Fragment() {

    companion object{
        fun newInstance() : UserUpdateFragment {
            return UserUpdateFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user_update, container, false)
        Log.d("TEST","UserUpdateFragment - onCreateView")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TEST","UserUpdateFragment - onViewCreated")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TEST","UserUpdateFragment - onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("TEST","UserUpdateFragment - onAttach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TEST", "UserUpdateFragment - onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TEST", "UserUpdateFragment - onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TEST", "UserUpdateFragment - onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TEST", "UserUpdateFragment - onDestroy")
    }
}