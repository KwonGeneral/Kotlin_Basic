package com.example.kotlin_basic.User

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_basic.R
import com.example.kotlin_basic.User.model.UserDataBase
import com.example.kotlin_basic.User.adapter.UserAdapter
import com.example.kotlin_basic.User.adapter.UserItem
import kotlinx.android.synthetic.main.fragment_user_search.*


class UserSearchFragment : Fragment() {
    companion object{
        fun newInstance() : UserSearchFragment {
            return UserSearchFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 검색 버튼 클릭 이벤트
        search_submit_btn.setOnClickListener {
            // 키보드 내리기 & 포커스 해제
            val mInputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            mInputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            search_user_name.clearFocus()

            // 리턴 값으로 userSearch() 객체를 받고, 명시적 선언을 위해 list, item 치환 사용.
            UserDataBase.getInstance(requireContext())?.userDao()?.userSearch(search_user_name.text.toString())?.also { list ->
                Log.d("TEST", "User List : $list")
                with(search_recycler) {
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
}