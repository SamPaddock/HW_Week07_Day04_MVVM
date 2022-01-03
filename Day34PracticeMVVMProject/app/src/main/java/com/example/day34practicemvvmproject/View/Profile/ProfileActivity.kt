package com.example.day34practicemvvmproject.View.Profile

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.net.toFile
import com.example.day34practicemvvmproject.Model.User
import com.example.day34practicemvvmproject.Util.Helper
import com.example.day34practicemvvmproject.View.Login.LoginActivity
import com.example.day34practicemvvmproject.databinding.ActivityProfileBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream
import java.io.FileInputStream

class ProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileBinding
    lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)

        var i = intent.getIntExtra("type", -1)

        if (i != 1){
            setProfileContent()
        } else {
            user = intent.getSerializableExtra("user") as User

            setSelectedUsersProfile(user)
        }


        setContentView(binding.root)
    }

    private fun setSelectedUsersProfile(user: User) {
        if (user.image.length > 0) {
            var imageArray = Base64.decode(user.image, Base64.DEFAULT)
            val bitMap = BitmapFactory.decodeByteArray(imageArray, 0, imageArray.size)
            binding.imageViewProfile.setImageBitmap(bitMap)
        } else {
            Picasso.get().load(Uri.parse(user.avatar)).into(binding.imageViewProfile)
        }

        binding.textViewProfileUsername.text = user.name
        binding.textViewProfileEmail.text = user.email

        binding.imageViewProfile.setOnClickListener {
            ImagePicker.with(this).crop().compress(180)
                .setImageProviderInterceptor {  }
                .start(5)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            val imageData = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageData)
            val decodedImage = encodeImage(bitmap)
            binding.imageViewProfile.setImageBitmap(bitmap)
            if (decodedImage != null) {
                Toast.makeText(this, "update", Toast.LENGTH_SHORT).show()
                user.image = decodedImage
                updateProfile(user)
            }
        }
    }

    fun updateProfile(user: User){
        val modelView: ProfileViewModel by viewModels()
        modelView.updateUser(user).observe(this){
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
            if (it == true){
                Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "failed to update profile", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun encodeImage(bm: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    private fun setProfileContent() {
        var sharedPref = this.getSharedPreferences("mvvm", MODE_PRIVATE)
        val user = Helper().getLoginSession(sharedPref)

        Picasso.get().load(Uri.parse(user.image)).into(binding.imageViewProfile)

        binding.textViewProfileUsername.text = user.username
        binding.textViewProfileEmail.text = user.email
    }
}