package com.example.medicapp.presentation.send_code

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.medicapp.MedicApp
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.databinding.ActivitySendCodeBinding
import com.example.medicapp.presentation.auth.AuthActivity
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
        fetch()
        observe()
    }

    override fun fetch(){
        binding.bt.setOnClickListener {
            lifecycleScope.launch {
                val email = binding.edEmail.text.toString()
                viewModel.sendAuthCode(email)
            }
        }
    }
    override fun observe(){
        viewModel.observe(this){
            when(it){
                is NetworkResult.Success -> {
                    Log.d("market_twits",  it.toString())
                    it.data?.message
                    launchAuthScreen("mknwylmcnximx@bugfoo.com")
                }
                is NetworkResult.Error -> {
                    Log.e("market_twits",  it.message.toString() )}
                is NetworkResult.Loading -> {
                    Log.d("market_twits",  "loading") }
            }
        }
    }
    private fun launchAuthScreen(email: String){
        val intent = AuthActivity.newIntentAddItem(email, this)
        startActivity(intent)
    }

}