package com.example.day34practicemvvmproject.View.MainView

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.day34practicemvvmproject.Model.User
import com.example.day34practicemvvmproject.Repository.UserRepository

class HomeViewModel: ViewModel() {

    fun getUsers(): LiveData<List<User>> {
        val livedata = MutableLiveData<List<User>>()
        UserRepository().getAllUsers().observeForever { users ->
            var newListOfUsers = users as MutableList
            newListOfUsers.removeIf { doesContainNull(it) == true }
            livedata.postValue(newListOfUsers)
        }
        return livedata
    }

    fun doesContainNull(user: User): Boolean{
        return if (user.email.isEmpty() == true
            || user.name.isEmpty() == true
            || user.image.isEmpty() == true) true else false
    }


}