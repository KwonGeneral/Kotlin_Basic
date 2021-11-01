package com.example.kotlin_basic.User.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_basic.R
import com.example.kotlin_basic.User.UserActivity
import com.example.kotlin_basic.User.contain.Define.Companion.UPDATE
import com.example.kotlin_basic.User.model.UserData
import com.example.kotlin_basic.User.model.UserDataBase
import kotlinx.android.synthetic.main.fragment_user_read_item.view.*


class UserAdapter constructor(var context:Context, var items:ArrayList<UserItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.fragment_user_read_item, parent, false)

        return VH(itemView)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh: VH = holder as VH

        with(vh.itemView) {
            // Null 체크 후, 명시적 선언을 위해 item으로 치환해서 사용.
            items[position]?.let { item ->
                read_user_name.text = item.name
                read_user_age.text = item.age
                read_user_gender.text = item.gender
                read_user_address.text = item.address
                read_user_phone.text = item.phone

                // 수정 버튼 클릭 시, 프레그먼트 전환.
                read_update_btn.setOnClickListener {
                    (context as UserActivity).changeFragment(UPDATE, item.id)
                }

                // 삭제 버튼 클릭 시, items 새로고침 및 DB 데이터 삭제.
                read_delete_btn.setOnClickListener {
                    UserDataBase.getInstance(context)?.userDao()?.userDelete(UserData(
                        item.id,
                        item.name,
                        item.age,
                        item.gender,
                        item.address,
                        item.phone
                    ))

                    items.removeAt(position)
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class VH constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        init{
        }
    }
}