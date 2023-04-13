package com.example.medicapp.presentation.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.medicapp.MedicApp
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.databinding.ActivityAuthBinding
import com.example.medicapp.presentation.base.NetworkUiModule
import kotlinx.coroutines.launch

class AuthActivity : AppCompatActivity(), NetworkUiModule {
    private lateinit var binding: ActivityAuthBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (application as MedicApp).authViewModel
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observe()
        fetch()
    }

    override fun fetch() {
        binding.btAuthCode.setOnClickListener {
            val email = intent.getStringExtra(EXTRA_EMAIL);
            val code = binding.edAuthCode.text.toString()
            lifecycleScope.launch {
                checkNotNull(email)
                viewModel.signIn(email, code)
            }
        }
    }

    override fun observe() {
        viewModel.observe(this) {
            when (it) {
                is NetworkResult.Success -> {
                    Log.d("market_twits", it.toString())
                }
                is NetworkResult.Error -> {
                    Log.e("market_twits", it.message.toString())
                }
                is NetworkResult.Loading -> {
                    Log.d("market_twits", "loading")
                }
            }
        }
    }
    companion object {
        private const val EXTRA_EMAIL = "extra_user_email"
        fun newIntentAddItem(email: String, context: Context): Intent {
            val intent = Intent(context, AuthActivity::class.java)
            intent.putExtra(EXTRA_EMAIL, email)
            return intent
        }
    }
}