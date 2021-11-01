package com.example.kotlin_basic.User

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_basic.R
import com.example.kotlin_basic.User.contain.Define.Companion.READ
import com.example.kotlin_basic.User.contain.Define.Companion.USER_ADDRESS
import com.example.kotlin_basic.User.contain.Define.Companion.USER_AGE
import com.example.kotlin_basic.User.contain.Define.Companion.USER_GENDER
import com.example.kotlin_basic.User.contain.Define.Companion.USER_NAME
import com.example.kotlin_basic.User.contain.Define.Companion.USER_PHONE
import com.example.kotlin_basic.User.contain.Define.Companion.pleasePrint
import com.example.kotlin_basic.User.model.UserData
import com.example.kotlin_basic.User.model.UserDataBase
import kotlinx.android.synthetic.main.fragment_user_create_update.*


class UserCreateFragment : Fragment() {
    companion object{
        fun newInstance() : UserCreateFragment {
            return UserCreateFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_create_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 생성 버튼 클릭 이벤트
        create_update_submit_btn.setOnClickListener {
            // EditText가 공백이면 해당 EditText에 대한 입력 요구 텍스트 그리기 함수
            fun checkEditText(text:String, tag:String): Boolean {
                return when(text.isEmpty()) {
                    true -> {
                        with(create_update_status_message) {
                            setTextColor(Color.RED)
                            setText(pleasePrint(tag))
                        }
                        true
                    }
                    false -> false
                }
            }

            // EditText 공백 체크
            if(checkEditText(create_update_user_name.text.toString(), USER_NAME)) return@setOnClickListener
            if(checkEditText(create_update_user_age.text.toString(), USER_AGE)) return@setOnClickListener
            if(checkEditText(create_update_user_gender.text.toString(), USER_GENDER)) return@setOnClickListener
            if(checkEditText(create_update_user_address.text.toString(), USER_ADDRESS)) return@setOnClickListener
            if(checkEditText(create_update_user_phone.text.toString(), USER_PHONE)) return@setOnClickListener

            // DB에 유저 정보 Insert
            UserDataBase.getInstance(requireContext())?.userDao()?.userCreate(
                UserData(
                    id = null,
                    name = create_update_user_name.text.toString(),
                    age = create_update_user_age.text.toString(),
                    gender = create_update_user_gender.text.toString(),
                    address = create_update_user_address.text.toString(),
                    phone = create_update_user_phone.text.toString()
                )
            )?.also {
                // 유저 정보를 성공적으로 입력 했을 시, Read Fragment로 이동
                (activity as UserActivity).changeFragment(READ)
            }
        }
    }
}