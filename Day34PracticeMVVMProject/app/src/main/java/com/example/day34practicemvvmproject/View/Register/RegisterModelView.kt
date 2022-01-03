package com.example.day34practicemvvmproject.View.Register

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.day34practicemvvmproject.Model.User
import com.example.day34practicemvvmproject.Model.Users
import com.example.day34practicemvvmproject.Repository.UserRepository
import com.example.day34practicemvvmproject.Util.Helper

class RegisterModelView: ViewModel() {

    fun register(sharedPref: SharedPreferences, user: Users): LiveData<Boolean> {
        val liveData = MutableLiveData<Boolean>()

        UserRepository().addUser(user).observeForever {
            Helper().loginSession(sharedPref, it)
            liveData.postValue(it.id.isNotEmpty())
        }
        return liveData
    }
}