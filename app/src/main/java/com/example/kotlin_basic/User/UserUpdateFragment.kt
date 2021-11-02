package com.example.kotlin_basic.User

import android.annotation.SuppressLint
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
        // 필수 파라미터를 받아서 번들을 구성해준다.
        fun newInstance(item_id:Int?) : UserUpdateFragment {
            return UserUpdateFragment().apply {
                item_id?.let {
                    arguments = Bundle().apply {
                        putString("id", item_id.toString())
                    }
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_create_update, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // bundle로 받은 argument 데이터를 통해, EditText 초기값 셋팅
        // 반환 값이 필요없고, 람다도 필요 없어서 run 사용.
        // 아래는 라이브 데이터를 사용해보자.
//        /*
        arguments?.run {
            getString("id")?.toLong()
        }?.let { user_id ->
            UserDataBase.getInstance(requireContext())?.apply {
                onUserReadOne(user_id.toInt())
            }
//                .userDao()?.apply {
//                userReadOne(user_id)?.also { list ->
//                    list[0]?.let {
//                        create_update_user_name.setText(it.name)
//                        create_update_user_age.setText(it.age)
//                        create_update_user_gender.setText(it.gender)
//                        create_update_user_address.setText(it.address)
//                        create_update_user_phone.setText(it.phone)
//                    }
//                }
//            }
        }
//        */

        ObserverViewModel.getInstance().update_user_name.observe(viewLifecycleOwner, {
            create_update_user_name.setText(it)
        })
        ObserverViewModel.getInstance().update_user_age.observe(viewLifecycleOwner, {
            create_update_user_age.setText(it)
        })
        ObserverViewModel.getInstance().update_user_gender.observe(viewLifecycleOwner, {
            create_update_user_gender.setText(it)
        })
        ObserverViewModel.getInstance().update_user_address.observe(viewLifecycleOwner, {
            create_update_user_address.setText(it)
        })
        ObserverViewModel.getInstance().update_user_phone.observe(viewLifecycleOwner, {
            create_update_user_phone.setText(it)
        })


        // 수정 버튼 클릭 이벤트
        create_update_submit_btn.setOnClickListener {
            // EditText가 공백이면 해당 EditText에 대한 입력 요구 텍스트 그리기 함수
            fun checkEditText(text:String, tag:String): Boolean {
                return when(text.isEmpty()) {
                    true -> {
                        with(create_update_status_message) {
                            setTextColor(Color.RED)
                            setText("[ $tag ] 입력 값이 올바르지 않습니다")
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

            // DB에 유저 정보 Update, 반환 값으로 객체를 받기 위해서 apply 사용.
            UserDataBase.getInstance(requireContext())?.onUserUpdate(UserData(
                id = arguments?.run { getString("id")?.toInt() },
                name = create_update_user_name.text.toString(),
                age = create_update_user_age.text.toString(),
                gender = create_update_user_gender.text.toString(),
                address = create_update_user_address.text.toString(),
                phone = create_update_user_phone.text.toString()
            ))?.let {
                // 아래에 있는 라이브데이터 값 변경은
                // UserDataBase에서 사용하면, UserDataBase의 onUserUpdate 독립성이 떨어짐.
                // 그렇기에 여기서 데이터 처리를 해주는 것이 맞아보임.
                ObserverViewModel.getInstance().screen_change_tag.value = READ
            }
        }
    }
}