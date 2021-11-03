package com.example.kotlin_basic.User.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_user")
data class UserData(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,  // PK

    @ColumnInfo(name = "name")
    val name: String? ="",  // 이름

    @ColumnInfo(name = "age")
    val age: String? ="",   // 나이

    @ColumnInfo(name = "gender")
    val gender: String?="M",  // 성별

    @ColumnInfo(name = "address")
    val address: String?="",  // 주소

    @ColumnInfo(name = "phone")
    val phone: String? = "",  // 연락처
) {
}
