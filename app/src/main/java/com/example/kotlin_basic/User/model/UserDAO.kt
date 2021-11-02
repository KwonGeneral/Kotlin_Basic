package com.example.kotlin_basic.User.model

import androidx.room.*

@Dao
interface UserDAO {
    // 유저 생성
    @Insert
    fun userCreate(vararg user: UserData)

    // 모든 유저 정보 조회
    @Query("SELECT * FROM tb_user")
    fun userReadAll(): List<UserData>

    // 조건에 따른 유저 정보 1개 조회
    @Query("SELECT * FROM tb_user WHERE id = :id ")
    fun userReadOne(id: Long): List<UserData>

    // 유저 정보 삭제
    @Query("DELETE FROM tb_user WHERE id = :id")
    fun userDeleteOne(id: Long)

    // 유저 정보 수정
    @Update
    fun userUpdate(vararg user: UserData)

    // 유저 정보 삭제
    @Delete
    fun userDelete(vararg user: UserData)

    // 이름을 통한 검색
    @Query("SELECT * FROM tb_user WHERE name = :name ")
    fun userSearch(name: String): List<UserData>

    // 테이블 청소
    @Query("DELETE FROM tb_user")
    fun userClear()
}