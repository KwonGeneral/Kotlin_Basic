package com.example.kotlin_basic.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlin_basic.R
import com.example.kotlin_basic.User.adapter.UserAdapter
import com.example.kotlin_basic.User.contain.Define.Companion.CREATE
import com.example.kotlin_basic.User.contain.Define.Companion.READ
import com.example.kotlin_basic.User.contain.Define.Companion.SEARCH
import com.example.kotlin_basic.User.contain.Define.Companion.UPDATE
import com.example.kotlin_basic.User.model.UserData
import com.example.kotlin_basic.User.model.UserDataBase
import kotlinx.android.synthetic.main.activity_user.*

// 유저 생성, 삭제, 검색 기능과
// 유저 리스트 목록 (조회) 기능을 만들어보자.
class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        // fragment에서는 lifrecycleowner를 viewLifecycleOwner로 주고
        // activity에서는 this로 매개변수 전달한다.
        ObserverViewModel.getInstance().screen_change_tag.observe(this, { ob ->
//            Log.d("TEST", "프레그먼트 전환 옵저버 : $ob")
            when(ob) {
                UPDATE -> return@observe
                else -> changeFragment(ob)
            }
        })
        ObserverViewModel.getInstance().update_get_user_id.observe(this, { id ->
            changeFragment(UPDATE, id)
        })

        // Create Fragment 전환
        user_create_btn.setOnClickListener {
            ObserverViewModel.getInstance().screen_change_tag.value = CREATE
//            changeFragment(CREATE)
        }

        // Read Fragment 전환
        user_read_btn.setOnClickListener {
            ObserverViewModel.getInstance().screen_change_tag.value = READ
//            changeFragment(READ)
        }

        // Update Fragment 전환
        user_search_btn.setOnClickListener {
            ObserverViewModel.getInstance().screen_change_tag.value = SEARCH
//            changeFragment(SEARCH)
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
                        UserCreateFragment()?.run {
                            ft.replace(R.id.main_frag, this, ty).commit()
                        }
                    }
                    READ -> {
                        UserReadFragment()?.run {
                            ft.replace(R.id.main_frag, this, ty).commit()
                        }
                    }
                    UPDATE -> {
                        /*
                        UserUpdateFragment.newInstance()?.run {
                            item_id?.let {
                                arguments = Bundle().apply {
                                    putString("id", item_id.toString())
                                }
                                ft.replace(R.id.main_frag, this, ty).commit()
                            }
                        }
                         */
                        // 위의 코드는 newInstance를 사용할 필요가 없다.
                        // newInstance를 사용해서 목적성을 표현하기 위해
                        // 필수 파라미터를 넣는다.
                        // 프레그먼트가 재 생성될 때, 파라미터는 받지 않고
                        // 기본 객체만을 가지고 생성되기 때문에, 런타임 에러가 발생한다.
                        // 번들은 프레그먼트가 생성되면서 붙기 때문에,
                        // 런타임 에러를 방지할 수 있다.

                        UserUpdateFragment.newInstance(item_id)?.run {
                            ft.replace(R.id.main_frag, this, ty).commit()
                        }
                    }
                    SEARCH -> {
                        UserSearchFragment()?.apply {
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