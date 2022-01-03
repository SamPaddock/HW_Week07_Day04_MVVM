package com.example.day34practicemvvmproject.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api2 {

    companion object {
        var BASE_URL = "https://61a322c3014e1900176deacb.mockapi.io/"

        private val retrofit: Retrofit
        init {
            retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build()
        }

        fun getInstance() = retrofit
    }
}