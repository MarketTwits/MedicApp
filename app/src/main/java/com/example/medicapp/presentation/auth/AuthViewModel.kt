package com.example.medicapp.presentation.auth

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.medicapp.domain.RepositoryImpl
import com.example.medicapp.presentation.send_code.SendCodeCommunication

class AuthViewModel(
    private val repository: RepositoryImpl,
    private val sendCodeCommunication: SendCodeCommunication,
) : ViewModel(), Observer<Any> {
    override fun onChanged(value: Any) {
        TODO("Not yet implemented")
    }
}