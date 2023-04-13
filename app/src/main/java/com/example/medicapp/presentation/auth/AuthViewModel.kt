package com.example.medicapp.presentation.auth

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.data.data_model.AuthTokenResponse
import com.example.medicapp.data.data_model.SendCodeResponseCloud
import com.example.medicapp.domain.RepositoryImpl
import com.example.medicapp.presentation.BaseObserve
import com.example.medicapp.presentation.Communication
import com.example.medicapp.presentation.send_code.SendCodeCommunication

class AuthViewModel(
    private val repository: RepositoryImpl,
    private val authCommunication: AuthCommunication,
) : ViewModel(), BaseObserve<NetworkResult<AuthTokenResponse>> {

    override fun observe(
        owner: LifecycleOwner,
        observer: Observer<NetworkResult<AuthTokenResponse>>,
    ) {
        super.observe(owner, observer)
        authCommunication.observe(owner, observer)
    }
    suspend fun signIn(email : String, authCode : String) {
        authCommunication.map(NetworkResult.Loading())
        val result = repository.signIn(email, authCode)
        authCommunication.map(result)
    }
}
interface AuthCommunication : Communication<NetworkResult<AuthTokenResponse>> {
    class Base() : Communication.Abstract<NetworkResult<AuthTokenResponse>>(), AuthCommunication
}