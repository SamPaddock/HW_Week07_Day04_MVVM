package com.example.day34practicemvvmproject.Network

import androidx.room.Update
import com.example.day34practicemvvmproject.Model.User
import com.example.day34practicemvvmproject.Model.Users
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    @GET("User")
    fun getUserByUsernameAndPassword(
        @Query("username")username: String
    ): Call<List<Users>>

    @POST("User")
    fun addNewUser(@Body user: Users): Call<Users>

    @GET("users")
    fun getAllUser(): Call<List<User>>

    @PUT("users/{id}")
    fun updateUser(@Path("id")id: String, @Body user: User): Call<User>

}