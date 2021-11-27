package com.example.day34practicemvvmproject.Util

import android.content.Context
import android.content.SharedPreferences
import com.example.day34practicemvvmproject.Model.User
import java.util.*


class Helper() {

    fun loginSession(sharedPref: SharedPreferences,user: User){
        val userShared = sharedPref.edit()
        userShared.putString("id", user.id)
        userShared.putString("username",user.username)
        userShared.putString("email",user.email)
        userShared.putString("photo",user.photo)
        userShared.commit()

        //save registration time
        val dateTime = Calendar.getInstance()
        dateTime.add(Calendar.MINUTE, 15)
        userShared.putLong("trial", dateTime.timeInMillis).commit()
    }

    fun isLoggedIn(sharedPref: SharedPreferences): Boolean{
        val userShared = sharedPref
        val savedDateTime = userShared.getLong("trial", 0.toLong())
        val currentDateTime = Calendar.getInstance().timeInMillis

        if (savedDateTime != null && savedDateTime <= currentDateTime) {
            userShared.edit().clear().commit()
            return false
        }
        return true
    }

    fun getLoginSession(sharedPref: SharedPreferences): User{
        val userShared = sharedPref
        val id = userShared.getString("id", "id").toString()
        val username = userShared.getString("username", "username").toString()
        val email = userShared.getString("email", "email").toString()
        val photo = userShared.getString("photo", "photo").toString()
        return User(id,email,"",username,photo)
    }

}