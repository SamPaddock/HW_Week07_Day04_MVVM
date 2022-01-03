package com.example.day34practicemvvmproject.View.MainView

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.day34practicemvvmproject.Model.User
import com.example.day34practicemvvmproject.View.Profile.ProfileActivity
import com.example.day34practicemvvmproject.databinding.ListItemUsersBinding
import com.squareup.picasso.Picasso

class HomeAdapter(var context: Context, var data: List<User>) : RecyclerView.Adapter<HomeHolder>() {

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val binding =
            ListItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {

        if (Base64.decode(data[position].image,Base64.DEFAULT).size > 4){
            var imageArray = Base64.decode(data[position].image,Base64.DEFAULT)
            val bitMap = BitmapFactory.decodeByteArray(imageArray, 0, imageArray.size)
            holder.binding.imageViewUserProfile.setImageBitmap(bitMap)
        } else {
            Log.d(TAG,"HomeAdapter: - onBindViewHolder: - setting avatar: ${data[position].avatar}")
            Picasso.get().load(Uri.parse(data[position].avatar)).into(holder.binding.imageViewUserProfile)
        }

        holder.binding.textViewUserEmail.text = data[position].email
        holder.binding.textViewUserUsername.text = data[position].name

        holder.itemView.setOnClickListener {
            var intent = Intent(context, ProfileActivity::class.java)
            intent.putExtra("type", 1)
            intent.putExtra("user", data[position])
            context.startActivity(intent)
        }
    }
}

class HomeHolder(val binding: ListItemUsersBinding) : RecyclerView.ViewHolder(binding.root)