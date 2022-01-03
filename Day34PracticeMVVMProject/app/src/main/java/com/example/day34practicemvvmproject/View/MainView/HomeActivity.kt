package com.example.day34practicemvvmproject.View.MainView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.day34practicemvvmproject.View.Profile.ProfileActivity
import com.example.day34practicemvvmproject.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var adapter: HomeAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        binding.fabViewProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        recyclerView = binding.recyclerViewUsers
        recyclerView.layoutManager = LinearLayoutManager(this)
        setAdapter()
    }

    private fun setAdapter(){
        val modelView: HomeViewModel by viewModels()
        modelView.getUsers().observe(this){
            adapter = HomeAdapter(this,it)
            recyclerView.adapter = adapter
        }
    }
}