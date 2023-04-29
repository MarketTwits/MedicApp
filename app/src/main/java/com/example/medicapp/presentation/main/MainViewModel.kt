package com.example.medicapp.presentation.main

import androidx.lifecycle.*
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.data.net.models.CatalogCloudItem
import com.example.medicapp.domain.RepositoryImpl
import com.example.medicapp.domain.SharedPrefDataSource
import kotlinx.coroutines.launch


class MainViewModel(
    private val repository: RepositoryImpl,
    private val sharedPrefDataSource: SharedPrefDataSource
) : ViewModel(){

     fun authUser() : Boolean{
        val token = sharedPrefDataSource.getAuthToken()
        return !token.isEmpty()
    }

}


