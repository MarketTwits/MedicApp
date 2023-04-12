package com.example.medicapp.presentation.auth

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.medicapp.MedicApp
import com.example.medicapp.R
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.databinding.ActivityAuthBinding
import com.example.medicapp.presentation.base.NetworkUiModule
import kotlinx.coroutines.launch

class AuthActivity : AppCompatActivity(), NetworkUiModule {
    private lateinit var binding : ActivityAuthBinding
    private lateinit var viewModel : AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        viewModel = (application as MedicApp).authViewModel
        setContentView(binding.root)
        getData()
        observe()
    }
    override fun getData(){
        lifecycleScope.launch {
            viewModel.sendAuthCode("vkhnvd@bugfoo.com")
        }
    }
    override fun observe(){
        viewModel.observe(this){
            when(it){
                is NetworkResult.Success -> {
                    Log.d("market_twits",  it.message.toString() )}
                is NetworkResult.Error -> {
                    Log.d("market_twits",  it.message.toString() )}
                is NetworkResult.Loading -> {
                    Log.d("market_twits",  "loading") }
            }
        }
    }
}