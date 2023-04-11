package com.example.medicapp.presentation.main

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.medicapp.MedicApp
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (application as MedicApp).mainViewModel
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
        observe()
    }
    private fun getData(){
        lifecycleScope.launch {
            viewModel.getCatalog()
        }
    }
    private fun observe(){
        viewModel.observe(this){
            when(it){
                is NetworkResult.Success<*> -> {
                    Log.d("market_twits", it.data.toString())
                }
                is NetworkResult.Failed -> {
                    Log.d("market_twits", it.errorMessage)
                }
                is NetworkResult.Loading -> {
                    Log.d("market_twits", "loading")
                }
            }
        }
    }
}