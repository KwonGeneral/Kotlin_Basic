package com.example.kotlin_basic.User

import androidx.lifecycle.MutableLiveData
import com.example.kotlin_basic.User.contain.Define.Companion.CREATE
import com.example.kotlin_basic.User.model.UserData

class ObserverViewModel {
    fun clearSearch() {
        search_result.value = ArrayList<UserData>()
    }

    // LiveData 선언
    // 옵저버(Ovserver)는 특정 변수를 관찰한다.
    var search_status = MutableLiveData<Int>()
    var search_result = MutableLiveData<List<UserData>>()

    var selected_user = MutableLiveData<UserData>()


    var update_get_user_id = MutableLiveData<Int>()
    var update_user_name = MutableLiveData<String>()
    var update_user_age = MutableLiveData<String>()
    var update_user_gender = MutableLiveData<String>()
    var update_user_address = MutableLiveData<String>()
    var update_user_phone = MutableLiveData<String>()
    val screen_change_tag = MutableLiveData<String>()
    init {
        screen_change_tag.value = CREATE
    }

    // 싱글톤 패턴을 사용해 getInstance() 함수 작성
    companion object {
        var obModel: ObserverViewModel? = null
        /*
        fun getInstance(): ObserverViewModel {
            obModel?.let {
                return obModel!!
            }
            return obModel!!
        }
         */
        // 위 처럼 return 값을 잘못 설정하면 앱이 죽거나
        // 반대로 ObserverViewModel() 만을 리턴하면 옵저버 값이 바뀌지 않는다.
        // 아래와 같이 사용해야 작동한다.
        fun getInstance(): ObserverViewModel {
            obModel?.let {
                return obModel!!
            }
            obModel = ObserverViewModel()
            return obModel!!
        }
    }
}