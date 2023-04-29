package com.example.medicapp.presentation.base_profile.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicapp.domain.SharedPrefDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val sharedPrefDataSource : SharedPrefDataSource
) : ViewModel() {

    fun cleanToken(){
        viewModelScope.launch(Dispatchers.IO) {
            sharedPrefDataSource.cleanUserToken()
        }
    }
}