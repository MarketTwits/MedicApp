package com.example.medicapp.presentation.auth.sign_in

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.example.medicapp.MedicApp
import com.example.medicapp.R
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.databinding.ActivitySignInBinding
import com.example.medicapp.presentation.auth.send_code.AuthActivity

class SignInActivity : AppCompatActivity(), HandleUiSignInActivity {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var viewModel: SignInViewModel
    lateinit var signInActivity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        viewModel = (application as MedicApp).signInViewModel
        signInActivity = this
        initView()
        observeViewModel()
        setContentView(binding.root)
    }

    override fun initView() {
        binding.etEmail.doAfterTextChanged {
            viewModel.validateEmail(it.toString())
        }
        binding.btnNext.setOnClickListener {
            if (viewModel.emailIsValid.value == true) {
                viewModel.sendAuthCode(binding.etEmail.text.toString())
            }
        }
    }

    override fun observeViewModel() {

        viewModel.emailIsValid.observe(this) { isValid ->
            if (isValid) {
                binding.btnNext.setBackgroundResource(R.drawable.filled_btn)
                binding.btnNext.isEnabled = true
            } else {
                binding.btnNext.setBackgroundResource(R.drawable.blocked_btn)
                binding.btnNext.isEnabled = false
            }
        }
        viewModel.sendCodeLiveData.observe(this) {
            when (it) {
                is NetworkResult.Success -> {
                    handleSuccess()
                    Log.d("market_twits", it.toString())
                }
                is NetworkResult.Error -> {
                    handleFailed(it.message.toString())
                    Log.e("market_twits", it.message.toString())
                }
                is NetworkResult.Loading -> {
                    handleLoading()
                    Log.d("market_twits", "loading")
                }
            }
        }
    }

    override fun handleSuccess() {
        val intent = AuthActivity.newIntentEmail(this, binding.etEmail.text.toString())
        startActivity(intent)
        handleLoadingAndError(loading = false, buttonState = true)
    }

    override fun handleLoading() {
        handleLoadingAndError(loading = true, buttonState = false)
    }

    override fun handleFailed(error: String) {
        handleLoadingAndError(loading = false, buttonState = true)
        binding.etEmail.error = error
    }

    override fun handleLoadingAndError(loading: Boolean, buttonState: Boolean) {
        binding.progressBar.isVisible = loading
        binding.btnNext.isActivated = buttonState
    }

    companion object {
        private const val EMAIL_KEY_BUNDLE = "email"
        val signIn = this
    }
}
