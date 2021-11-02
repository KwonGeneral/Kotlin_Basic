package com.example.kotlin_basic.User.model

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlin_basic.User.ObserverViewModel
import com.example.kotlin_basic.User.contain.Define
import com.example.kotlin_basic.User.contain.Define.Companion.READ
import kotlinx.android.synthetic.main.fragment_user_create_update.*
import java.lang.Thread.sleep

@Database(entities = [UserData::class], version = 2, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {
    abstract fun userDao(): UserDAO

    companion object {
        // Thread를 동기화 하기 위해서 Synchronized를 사용한다.
        // Thread는 Synchronized 메소드에 들어가기 위해 Lock을 얻고 메소드가 끝이나면 Lock을 반환한다.
        // 예를 들면, 자칫 여러 인스턴스를 생성될 수 있는 경우에 하나만 생성하도록 하기 위해 Synchronized 데코레이터를 통해 방지한다.
        /*
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
         */
        // 위와 같은 구조는 싱글톤 패턴이라 볼 수 없다.
        // 호출할 때 마다, 새로운 instance를 계속 생성하는 것은 싱글톤 패턴이 아니다.
        // 아래가 getInstace와 맞는 싱글톤 패턴이다.
        // 하나의 instance만을 메모리에 올려서 사용하는 것이 싱글톤 패턴이다.
        val instance :UserDataBase? = null
        @Synchronized
        fun getInstance(context: Context): UserDataBase? {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                        context.applicationContext,
                        UserDataBase::class.java,
                        "board_db"
                    )
                    .fallbackToDestructiveMigration()  // 스키마(=테이블 설계 구조) 버전 변경 가능
                    .allowMainThreadQueries()  // Main Thread에서 DB에 IO(Input/Output)를 가능하게 함
                    .build()
        }
    }
    fun onUserCreate(user: UserData) {
        userDao()?.apply {
            userCreate(user)
        }
    }

    fun onUserReadOne(id: Int) {
        // 아래는 라이브 데이터를 사용해보자.
        /*
        userDao()?.userReadOne(id.toLong())?.also { list ->
            list[0]?.let {
                create_update_user_name.setText(it.name)
                create_update_user_age.setText(it.age)
                create_update_user_gender.setText(it.gender)
                create_update_user_address.setText(it.address)
                create_update_user_phone.setText(it.phone)
            }
        }
         */
        userDao()?.userReadOne(id.toLong())?.also { list ->
            list[0]?.let {
                ObserverViewModel.getInstance().update_user_name.value = it.name
                ObserverViewModel.getInstance().update_user_age.value = it.age
                ObserverViewModel.getInstance().update_user_gender.value = it.gender
                ObserverViewModel.getInstance().update_user_address.value = it.address
                ObserverViewModel.getInstance().update_user_phone.value = it.phone
            }
        }
    }

    fun onUserUpdate(user: UserData) {
        userDao()?.apply {
            userUpdate(user)
        }
    }

    fun onUserDelete(id: Int) {
        userDao()?.apply {
            userDeleteOne(id.toLong())
        }
    }

    fun onUserSearch(name: String) {
        userDao()?.apply {
            userSearch(name)?.let {
                ObserverViewModel.getInstance().search_result.value = it
            }
        }
    }
}
