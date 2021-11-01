package com.example.kotlin_basic.User.contain

class Define {
    companion object {
        // 유저 정보
        const val USER_NAME:String = "이름"
        const val USER_AGE:String = "나이"
        const val USER_GENDER:String = "성별"
        const val USER_ADDRESS:String = "주소"
        const val USER_PHONE:String = "연락처"

        // 프레그먼트 태그
        const val CREATE:String = "Create"
        const val READ:String = "Read"
        const val UPDATE:String = "Update"
        const val SEARCH:String = "Search"

        // 유저 정보 입력 값 오류
        fun pleasePrint(str:String): String {
            return "[ $str ] 입력 값이 올바르지 않습니다"
        }
    }
}