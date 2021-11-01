package com.example.kotlin_basic.User.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserData::class], version = 2, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {
    abstract fun userDao(): UserDAO

    companion object {
        // Thread를 동기화 하기 위해서 Synchronized를 사용한다.
        // Thread는 Synchronized 메소드에 들어가기 위해 Lock을 얻고 메소드가 끝이나면 Lock을 반환한다.
        // 예를 들면, 자칫 여러 인스턴스를 생성될 수 있는 경우에 하나만 생성하도록 하기 위해 Synchronized 데코레이터를 통해 방지한다.
        @Synchronized
        fun getInstance(context: Context): UserDataBase? {
            val instance = UserDataBase?.let {
                Room.databaseBuilder(
                    context.applicationContext,
                    UserDataBase::class.java,
                    "board_db"
                )
                .fallbackToDestructiveMigration()  // 스키마(=테이블 설계 구조) 버전 변경 가능
                .allowMainThreadQueries()  // Main Thread에서 DB에 IO(Input/Output)를 가능하게 함
                .build()
            }

            return instance
        }
    }
}
