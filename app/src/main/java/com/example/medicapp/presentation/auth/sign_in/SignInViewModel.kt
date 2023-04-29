package com.example.medicapp.presentation.auth.sign_in

import android.text.TextUtils
import androidx.lifecycle.*
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.data.net.models.SendCodeResponseCloud
import com.example.medicapp.domain.RepositoryImpl

import kotlinx.coroutines.launch

class SignInViewModel(
    private val repository: RepositoryImpl,
) : ViewModel() {

    private val _sendCodeLiveData = MutableLiveData<NetworkResult<SendCodeResponseCloud>>()
    val sendCodeLiveData : LiveData<NetworkResult<SendCodeResponseCloud>> = _sendCodeLiveData

    private val _emailIsValid = MutableLiveData<Boolean>()
    val emailIsValid = _emailIsValid

     fun sendAuthCode(email : String) {
         viewModelScope.launch {
             _sendCodeLiveData.value = NetworkResult.Loading()
             val result = repository.sendEmail(email)
             _sendCodeLiveData.value = result
         }
    }
    fun validateEmail(email: String) = viewModelScope.launch {
        _emailIsValid.value = checkEmail(email)
    }
    private fun checkEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
