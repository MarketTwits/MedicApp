package com.example.medicapp.presentation.main

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.medicapp.MedicApp
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.data.data_model.CatalogCloudItem
import com.example.medicapp.databinding.ActivityMainBinding
import com.example.medicapp.presentation.auth.AuthActivity
import com.example.medicapp.presentation.base.NetworkUiModule
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), NetworkUiModule {

    lateinit var binding : ActivityMainBinding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (application as MedicApp).mainViewModel
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
        observe()
        setupListener()
    }
    private fun setupListener(){
        binding.button.setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
        }
    }
    override fun getData(){
        lifecycleScope.launch {
            viewModel.getCatalog()
        }
    }
    override fun observe(){
        viewModel.observe(this){
            when(it){
                is NetworkResult.Success -> {Log.d("market_twits",  it.data.toString() )}
                is NetworkResult.Error -> {Log.d("market_twits",  it.message.toString() )}
                is NetworkResult.Loading -> {Log.d("market_twits",  "loading") }
            }
        }
    }
}