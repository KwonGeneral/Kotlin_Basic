package com.example.kotlin_basic.User

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_basic.R
import kotlinx.android.synthetic.main.fragment_user_delete.*


class UserDeleteFragment : Fragment() {

    companion object{
        fun newInstance() : UserDeleteFragment {
            return UserDeleteFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user_delete, container, false)
        Log.d("TEST","UserDeleteFragment - onCreateView")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TEST","UserDeleteFragment - onViewCreated")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TEST","UserDeleteFragment - onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("TEST","UserDeleteFragment - onAttach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TEST", "UserDeleteFragment - onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TEST", "UserDeleteFragment - onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TEST", "UserDeleteFragment - onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TEST", "UserDeleteFragment - onDestroy")
    }
}