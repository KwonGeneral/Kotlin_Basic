package com.example.kotlin_basic.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlin_basic.R
import kotlinx.android.synthetic.main.activity_user.*

// 유저 생성, 삭제, 검색 기능과
// 유저 리스트 목록 (조회) 기능을 만들어보자.
class UserActivity : AppCompatActivity() {

    companion object {
        val CREATE:String = "Create"
        val READ:String = "Read"
        val UPDATE:String = "Update"
        val DELETE:String = "Delete"
        val SEARCH:String = "Search"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        user_create_btn.setOnClickListener {
            changeFragment(CREATE)
        }
        user_read_btn.setOnClickListener {
            changeFragment(READ)
        }
        user_update_btn.setOnClickListener {
            changeFragment(UPDATE)
        }
        user_delete_btn.setOnClickListener {
            changeFragment(DELETE)
        }
        user_search_btn.setOnClickListener {
            changeFragment(SEARCH)
        }
    }

    fun changeFragment(type:String?) {
        type?.let {
            checkFragment(it)
        }
    }

    private fun checkFragment(type:String?) {
        // Let -> Require Not Null
        supportFragmentManager?.beginTransaction()?.let { ft ->
            type?.let { ty ->
                when(ty) {
                    CREATE -> {
                        UserCreateFragment.newInstance()?.apply {
                            ft.replace(R.id.main_frag, this, ty).commit()
                        }
                    }
                    READ -> {
                        UserReadFragment.newInstance()?.apply {
                            ft.replace(R.id.main_frag, this, ty).commit()
                        }
                    }
                    UPDATE -> {
                        UserUpdateFragment.newInstance()?.apply {
                            ft.replace(R.id.main_frag, this, ty).commit()
                        }
                    }
                    DELETE -> {
                        UserDeleteFragment.newInstance()?.apply {
                            ft.replace(R.id.main_frag, this, ty).commit()
                        }
                    }
                    SEARCH -> {
                        UserSearchFragment.newInstance()?.apply {
                            ft.replace(R.id.main_frag, this, ty).commit()
                        }
                    }
                    else -> {
                        Log.d("TEST", "! Type Error !")
                    }
                }
            }
        }
    }
}