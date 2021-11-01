package com.example.kotlin_basic.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlin_basic.R
import com.example.kotlin_basic.User.contain.Define.Companion.CREATE
import com.example.kotlin_basic.User.contain.Define.Companion.READ
import com.example.kotlin_basic.User.contain.Define.Companion.SEARCH
import com.example.kotlin_basic.User.contain.Define.Companion.UPDATE
import kotlinx.android.synthetic.main.activity_user.*

// 유저 생성, 삭제, 검색 기능과
// 유저 리스트 목록 (조회) 기능을 만들어보자.
class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        // Create Fragment 전환
        user_create_btn.setOnClickListener {
            changeFragment(CREATE)
        }

        // Read Fragment 전환
        user_read_btn.setOnClickListener {
            changeFragment(READ)
        }

        // Update Fragment 전환
        user_search_btn.setOnClickListener {
            changeFragment(SEARCH)
        }
    }

    fun changeFragment(type:String?, item_id:Int? = null) {
        type?.let { ty ->
            checkFragment(ty, item_id)
        }
    }

    private fun checkFragment(type:String?, item_id:Int? = null) {
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
                        // 리턴 값으로 newInstance() 객체를 받고, item_id가 null이 아닐 경우 bundle에 데이터 셋팅 후 프레그먼트 전환
                        UserUpdateFragment.newInstance()?.apply {
                            item_id?.let {
                                arguments = Bundle().apply {
                                    putString("id", item_id.toString())
                                }
                                ft.replace(R.id.main_frag, this, ty).commit()
                            }
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