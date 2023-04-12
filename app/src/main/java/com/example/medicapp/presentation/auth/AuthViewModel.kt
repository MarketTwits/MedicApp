package com.example.medicapp.presentation.auth

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.data.data_model.CatalogCloud
import com.example.medicapp.domain.RepositoryImpl
import com.example.medicapp.presentation.AuthCommunication
import com.example.medicapp.presentation.BaseObserve
import com.example.medicapp.presentation.NetworkCommunication
import okhttp3.ResponseBody

class AuthViewModel(
    private val repository: RepositoryImpl,
    private val authCommunication: AuthCommunication,
) : ViewModel(), BaseObserve<NetworkResult<ResponseBody>> {

    override fun observe(
        owner: LifecycleOwner,
        observer: Observer<NetworkResult<ResponseBody>>,
    ) {
        authCommunication.observe(owner, observer)
    }
    suspend fun sendAuthCode(email : String) {
        authCommunication.map(NetworkResult.Loading())
        val result = repository.sendEmail(email)
        authCommunication.map(result)
    }
}