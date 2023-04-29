package com.example.medicapp.presentation.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.medicapp.MedicApp
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.databinding.ActivityMainBinding
import com.example.medicapp.presentation.auth.sign_in.SignInActivity
import com.example.medicapp.presentation.base_profile.UserActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (application as MedicApp).mainViewModel
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       auth()
    }
    private fun auth(){
        if (viewModel.authUser()){
            launchActivity(UserActivity::class.java)
        }else{
            launchActivity(SignInActivity::class.java)
        }
    }
    private fun <T>launchActivity(activity : Class<T>){
        startActivity(Intent(this, activity))
        finish()
    }
}