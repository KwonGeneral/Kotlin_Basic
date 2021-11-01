package com.example.kotlin_basic.User.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_user")
data class UserData(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "age")
    val age: String?,

    @ColumnInfo(name = "gender")
    val gender: String?,

    @ColumnInfo(name = "address")
    val address: String?,

    @ColumnInfo(name = "phone")
    val phone: String?,
) {
}
