package com.example.kotlin_basic.User

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_basic.R


class UserReadFragment : Fragment() {

    companion object{
        fun newInstance() : UserReadFragment {
            return UserReadFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user_read, container, false)
        Log.d("TEST","UserReadFragment - onCreateView")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TEST","UserReadFragment - onViewCreated")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TEST","UserReadFragment - onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("TEST","UserReadFragment - onAttach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TEST", "UserReadFragment - onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TEST", "UserReadFragment - onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TEST", "UserReadFragment - onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TEST", "UserReadFragment - onDestroy")
    }
}