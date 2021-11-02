package com.example.kotlin_basic.User

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_basic.R
import com.example.kotlin_basic.User.model.UserDataBase
import com.example.kotlin_basic.User.adapter.UserAdapter
import com.example.kotlin_basic.User.adapter.UserItem
import com.example.kotlin_basic.User.contain.Define.Companion.READ
import com.example.kotlin_basic.User.contain.Define.Companion.UPDATE
import com.example.kotlin_basic.User.model.UserData
import kotlinx.android.synthetic.main.fragment_user_read.*


class UserReadFragment : Fragment() {
    companion object{
        fun newInstance() : UserReadFragment {
            return UserReadFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_read, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 반환 값으로 객체를 받기 위해서 apply 사용.
        UserDataBase.getInstance(requireContext())?.userDao()?.apply {
            userReadAll()?.let { list ->
                with(read_recycler) {
                    layoutManager = LinearLayoutManager(context)

                    // UserAdapter의 매개변수로 UserItem 객체를 넘기기 위해 also 사용.
                    adapter = UserAdapter(requireContext(), arrayListOf<UserItem>().also { item ->
                        for(kk in list) {
                            item.add(UserItem(kk.id!!, kk.name!!, kk.age!!, kk.gender!!, kk.address!!, kk.phone!!))
                        }
                    },
                        // object를 쓰는 것과 안 쓰는 것의 차이는
                        // override와 연관이 있어 보인다.
                        // object를 쓰지 않는다면
//                        UserAdapter.onAdapterBtnClick.onUpdate(:int)
                        // 위와 같이 사용해야 한다.
                        object: UserAdapter.onAdapterBtnClick {
                            override fun onUpdate(id: Int) {
                                // activity도 null 체크를 해주는게 좋다.
//                                activity?.let {
//                                    (it as UserActivity).changeFragment(UPDATE)
//                                }
                                ObserverViewModel.getInstance().update_get_user_id.value = id
                                Log.d("TEST", "값찾기 : ${ObserverViewModel.getInstance().update_get_user_id.value}")
//                                ObserverViewModel.getInstance().screen_change_tag.value = UPDATE
                            }

                            override fun onDelete(id: Int) {
                                UserDataBase.getInstance(context)?.onUserDelete(id)
                            }
                        })
                }
            }
        }
    }
}