package com.example.kotlin_basic.User.model

import androidx.room.*

@Dao
interface UserDAO {
    // Create
    @Insert
    fun userCreate(vararg user: UserData)

    // Read
    @Query("SELECT * FROM tb_user")
    fun userRead(): List<UserData>

    // Update
    @Update
    fun userUpdate(vararg user: UserData)

    // Delete
    @Delete
    fun userDelete(vararg user: UserData)

    // Search
//    @Query("SELECT * FROM tb_user WHERE name = :name")
//    fun userSearch(name: String)

    // Clear
    @Query("DELETE FROM tb_user")
    fun userClear()
}