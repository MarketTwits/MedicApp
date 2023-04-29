package com.example.medicapp.presentation.auth.send_code

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.data.net.models.SignInResponseCloud
import com.example.medicapp.domain.RepositoryImpl
import com.example.medicapp.domain.SharedPrefDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(
    private val repository: RepositoryImpl,
    private val sharedPrefDataSource: SharedPrefDataSource
) : ViewModel() {
    private val _emailLiveData = MutableLiveData<NetworkResult<SignInResponseCloud>>()
    val emailLiveData : LiveData<NetworkResult<SignInResponseCloud>> = _emailLiveData

    fun sendAuthCode(email: String, authCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _emailLiveData.postValue( (NetworkResult.Loading()))
            val result = repository.signIn(email, authCode)
            withContext(Dispatchers.Main){
                _emailLiveData.value = result
            }
        }
    }
    fun saveUserToken(token : String){
        viewModelScope.launch(Dispatchers.IO) {
            sharedPrefDataSource.saveAuthToken(token)
        }
    }
}
