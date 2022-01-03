package com.example.day34practicemvvmproject.View.Profile

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.day34practicemvvmproject.Model.User
import com.example.day34practicemvvmproject.Repository.UserRepository
import java.util.logging.Handler

class ProfileViewModel: ViewModel() {

    fun updateUser(user: User): LiveData<Boolean> {
        val liveData = MutableLiveData<Boolean>()

        UserRepository().updateUser(user).observeForever {
            Log.d(TAG,"ProfileViewModel: - updateUser: - : ${it}")

            liveData.postValue(it.id.isNotEmpty())
        }

        return liveData
    }

}