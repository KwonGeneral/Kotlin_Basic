package com.example.kotlin_basic.User

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_basic.R
import com.example.kotlin_basic.User.contain.Define
import com.example.kotlin_basic.User.contain.Define.Companion.READ
import com.example.kotlin_basic.User.model.UserData
import com.example.kotlin_basic.User.model.UserDataBase
import kotlinx.android.synthetic.main.fragment_user_create_update.*


class UserUpdateFragment : Fragment() {
    companion object{
        fun newInstance() : UserUpdateFragment {
            return UserUpdateFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_create_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // bundle로 받은 argument 데이터를 통해, EditText 초기값 셋팅
        arguments?.run {
            getString("id")?.toLong()
        }?.let { user_id ->
            UserDataBase.getInstance(requireContext())?.userDao()?.userReadOne(user_id)?.also { list ->
                list[0]?.let {
                    create_update_user_name.setText(it.name)
                    create_update_user_age.setText(it.age)
                    create_update_user_gender.setText(it.gender)
                    create_update_user_address.setText(it.address)
                    create_update_user_phone.setText(it.phone)
                }
            }
        }

        // 수정 버튼 클릭 이벤트
        create_update_submit_btn.setOnClickListener {
            // EditText가 공백이면 해당 EditText에 대한 입력 요구 텍스트 그리기 함수
            fun checkEditText(text:String, tag:String): Boolean {
                return when(text.isEmpty()) {
                    true -> {
                        with(create_update_status_message) {
                            setTextColor(Color.RED)
                            setText(Define.pleasePrint(tag))
                        }
                        true
                    }
                    false -> false
                }
            }

            // EditText 공백 체크
            if(checkEditText(create_update_user_name.text.toString(), Define.USER_NAME)) return@setOnClickListener
            if(checkEditText(create_update_user_age.text.toString(), Define.USER_AGE)) return@setOnClickListener
            if(checkEditText(create_update_user_gender.text.toString(), Define.USER_GENDER)) return@setOnClickListener
            if(checkEditText(create_update_user_address.text.toString(), Define.USER_ADDRESS)) return@setOnClickListener
            if(checkEditText(create_update_user_phone.text.toString(), Define.USER_PHONE)) return@setOnClickListener

            // DB에 유저 정보 Update
            UserDataBase.getInstance(requireContext())?.userDao()?.userUpdate(
                UserData(
                    id = arguments?.run { getString("id")?.toInt() },
                    name = create_update_user_name.text.toString(),
                    age = create_update_user_age.text.toString(),
                    gender = create_update_user_gender.text.toString(),
                    address = create_update_user_address.text.toString(),
                    phone = create_update_user_phone.text.toString()
                )
            )?.also {
                // 유저 정보를 성공적으로 수정 했을 시, Read Fragment로 이동
                (activity as UserActivity).changeFragment(READ)
            }
        }
    }
}