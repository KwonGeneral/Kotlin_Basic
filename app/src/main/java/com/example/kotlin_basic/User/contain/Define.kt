package com.example.kotlin_basic.User.contain

class Define {
    companion object {
        val USER_NAME:String = "Name"
        val USER_AGE:String = "Age"
        val USER_GENDER:String = "Gender"
        val USER_ADDRESS:String = "Address"
        val USER_PHONE:String = "Phone"
        val USER_CREATE_SUCCESS:String = "User Create Success"

        fun pleasePrint(str:String): String {
            return "Please check your $str"
        }
    }
}