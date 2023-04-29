package com.example.medicapp.presentation.auth.send_code

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.medicapp.MedicApp
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.databinding.ActivityAuthBinding
import com.example.medicapp.presentation.auth.sign_in.SignInActivity
import com.example.medicapp.presentation.base_profile.UserActivity

class AuthActivity : AppCompatActivity(), HandleAuthCodeActivity {
    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: ActivityAuthBinding
    private var editTextList = mutableListOf<EditText>()
    private var currentEditTextIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (application as MedicApp).authViewModel
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handlePinCode()
        observeViewModel()
    }

    private fun handlePinCode() {
        binding.btnBack.setOnClickListener {
            finish()
        }
        editTextList.addAll(
            listOf(
                binding.etCode1,
                binding.etCode2,
                binding.etCode3,
                binding.etCode4
            )
        )
        // Set listeners for each EditText view
        for ((index, editText) in editTextList.withIndex()) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // If user enters a digit, move to next EditText view
                    if (s?.length == 1 && index < editTextList.lastIndex) {
                        editTextList[index + 1].requestFocus()
                        currentEditTextIndex = index + 1
                    }

                    // If all EditText views have been filled, display the entered PIN and show a Toast message
                    if (editTextList.all { it.text.isNotEmpty() }) {
                        val pin = editTextList.joinToString("") { it.text.toString() }
                        viewModel.sendAuthCode(getEmail(), pin)
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }

    private fun getEmail(): String {
        val email = this.intent.getStringExtra(EMAIL_KEY_BUNDLE)
        return checkNotNull(email)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_CLEAR) {
            // Clear the contents of all EditText views
            for (editText in editTextList) {
                editText.text.clear()
            }
            // Set focus to the first EditText view
            editTextList[0].requestFocus()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun handleSuccess(token: String) {
        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
        viewModel.saveUserToken(token)
        handleLoadingAndError(loading = false)
        val intent = Intent(this, UserActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }

    override fun handleLoading() {
        handleLoadingAndError(loading = true)
    }

    override fun handleFailed(error: String) {
        handleLoadingAndError(loading = false)
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun handleLoadingAndError(loading: Boolean) {
        binding.progressBarAuth.isVisible = loading
    }

    override fun observeViewModel() {
        viewModel.emailLiveData.observe(this) {
            when (it) {
                is NetworkResult.Success -> {
                    if (it.data?.token != null) {
                        handleSuccess(it.data.token)
                    } else {
                        handleFailed("Token don't save")
                    }
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

    companion object {
        fun newIntentEmail(context: Context, email: String): Intent {
            val intent = Intent(context, AuthActivity::class.java)
            intent.putExtra(EMAIL_KEY_BUNDLE, email)
            return intent
        }

        private const val EMAIL_KEY_BUNDLE = "email"
    }

}
