package com.example.day34practicemvvmproject.Model

import java.io.Serializable

data class User(
    val avatar: String,
    val email: String,
    val id: String,
    var image: String,
    val name: String
): Serializable