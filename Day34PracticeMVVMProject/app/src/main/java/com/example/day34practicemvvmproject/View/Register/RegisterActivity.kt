package com.example.day34practicemvvmproject.View.Register

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.day34practicemvvmproject.Model.User
import com.example.day34practicemvvmproject.Model.Users
import com.example.day34practicemvvmproject.R
import com.example.day34practicemvvmproject.View.MainView.HomeActivity
import com.example.day34practicemvvmproject.databinding.ActivityRegisterBinding
import com.squareup.picasso.Picasso

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)

        val editTextImageUri = binding.editTextRegisteredImageUri
        editTextImageUri.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus != true){
                Picasso.get().load(Uri.parse(editTextImageUri.text.toString()))
                    .into(binding.imageViewRegisteredUser)
            }
        }

        val viewModel: RegisterModelView by viewModels()

        binding.buttonRegister.setOnClickListener {

            var sharedPref = this.getSharedPreferences("mvvm", Context.MODE_PRIVATE)
            viewModel.register(sharedPref,
                Users(
                    "0",
                    binding.editTextRegisteredEmail.text.toString(),
                    binding.editTextRegisteredPassword.text.toString(),
                    binding.editTextRegisteredUsername.text.toString(),
                    editTextImageUri.text.toString()
                )
            ).observe(this){
                if (it == true){
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this,
                        getString(R.string.register_failed_unknown),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }



        setContentView(binding.root)
    }
}