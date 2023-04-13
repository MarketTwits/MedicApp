package com.example.medicapp.presentation.send_code

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.medicapp.MedicApp
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.databinding.ActivityAuthBinding
import com.example.medicapp.databinding.ActivitySendCodeBinding
import com.example.medicapp.presentation.base.NetworkUiModule
import kotlinx.coroutines.launch

class SendCodeActivity : AppCompatActivity(), NetworkUiModule {
    private lateinit var binding : ActivitySendCodeBinding
    private lateinit var viewModel : SendCodeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendCodeBinding.inflate(layoutInflater)
        viewModel = (application as MedicApp).sendCodeViewModel
        setContentView(binding.root)
        getData()
        observe()
    }
    override fun getData(){
        lifecycleScope.launch {
            viewModel.sendAuthCode("down this shit it hit me fast !")
        }
    }
    override fun observe(){
        viewModel.observe(this){
            when(it){
                is NetworkResult.Success -> {
                    Log.d("market_twits",  it.toString() )}
                is NetworkResult.Error -> {
                    Log.e("market_twits",  it.message.toString() )}
                is NetworkResult.Loading -> {
                    Log.d("market_twits",  "loading") }
            }
        }
    }
}