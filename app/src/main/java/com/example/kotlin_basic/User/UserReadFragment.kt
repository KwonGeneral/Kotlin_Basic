package com.example.kotlin_basic.User

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_basic.R
import com.example.kotlin_basic.User.model.UserDataBase
import com.example.kotlin_basic.User.adapter.UserAdapter
import com.example.kotlin_basic.User.adapter.UserItem
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

        // 리턴 값으로 userReadAll(), UserItem() 객체를 받고, 명시적 선언을 위해 list, item 치환 사용.
        UserDataBase.getInstance(requireContext())?.userDao()?.userReadAll()?.also { list ->
            with(read_recycler) {
                layoutManager = LinearLayoutManager(context)
                adapter = UserAdapter(requireContext(), arrayListOf<UserItem>().also { item ->
                    for(kk in list) {
                        item.add(UserItem(kk.id!!, kk.name!!, kk.age!!, kk.gender!!, kk.address!!, kk.phone!!))
                    }
                })
            }
        }
    }
}