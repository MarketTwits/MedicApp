package com.example.medicapp.presentation.send_code

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.data.data_model.SendCodeResponseCloud
import com.example.medicapp.domain.RepositoryImpl

import com.example.medicapp.presentation.BaseObserve
import com.example.medicapp.presentation.Communication

class SendCodeViewModel(
    private val repository: RepositoryImpl,
    private val sendCodeCommunication: SendCodeCommunication,
) : ViewModel(), BaseObserve<NetworkResult<SendCodeResponseCloud>> {

    override fun observe(
        owner: LifecycleOwner,
        observer: Observer<NetworkResult<SendCodeResponseCloud>>,
    ) {
        sendCodeCommunication.observe(owner, observer)
    }
    suspend fun sendAuthCode(email : String) {
        sendCodeCommunication.map(NetworkResult.Loading())
        val result = repository.sendEmail(email)
        sendCodeCommunication.map(result)
    }
}
interface SendCodeCommunication : Communication<NetworkResult<SendCodeResponseCloud>> {
    class Base() : Communication.Abstract<NetworkResult<SendCodeResponseCloud>>(), SendCodeCommunication
}