package com.example.kotlin_basic.User

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_basic.R
import com.example.kotlin_basic.User.model.UserDataBase
import com.example.kotlin_basic.User.adapter.UserAdapter
import com.example.kotlin_basic.User.adapter.UserItem
import com.example.kotlin_basic.User.contain.Define
import com.example.kotlin_basic.User.contain.Define.Companion.READ
import com.example.kotlin_basic.User.contain.Define.Companion.UPDATE
import com.example.kotlin_basic.User.model.UserData
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

        ObserverViewModel.getInstance().search_result.apply {
            observe(viewLifecycleOwner, { list ->
                Log.d("TEST", "하하하 -> $list")
                with(search_recycler) {
                    layoutManager = LinearLayoutManager(context)
                    adapter = UserAdapter(requireContext(), arrayListOf<UserItem>().also { item ->
                        for(kk in list) {
                            item.add(UserItem(kk.id!!, kk.name!!, kk.age!!, kk.gender!!, kk.address!!, kk.phone!!))
                        }
                    }, object: UserAdapter.onAdapterBtnClick{
                        override fun onUpdate(id: Int) {
                            // activity도 null 체크를 해주는게 좋다.
//                            activity?.let {
//                                (it as UserActivity).changeFragment(Define.READ)
//                            }
                            ObserverViewModel.getInstance().update_get_user_id.value = id
                            ObserverViewModel.getInstance().screen_change_tag.value = UPDATE
                        }

                        override fun onDelete(id: Int) {
                            UserDataBase.getInstance(context)?.onUserDelete(id)
                        }
                    })
                }
            })
        }

        // 검색 버튼 클릭 이벤트
        search_submit_btn.setOnClickListener {
            // 키보드 내리기 & 포커스 해제
            val mInputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            mInputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            search_user_name.clearFocus()

            // 리턴 값으로 userSearch() 객체를 받고, 명시적 선언을 위해 list, item 치환 사용.
            UserDataBase.getInstance(requireContext())?.apply {
                onUserSearch(search_user_name.text.toString())
            }
        }
    }
}