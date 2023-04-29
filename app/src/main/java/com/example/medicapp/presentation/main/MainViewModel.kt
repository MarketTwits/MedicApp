package com.example.medicapp.presentation.main

import androidx.lifecycle.*
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.data.net.models.CatalogCloudItem
import com.example.medicapp.domain.RepositoryImpl



class MainViewModel(
    private val repository: RepositoryImpl,
) : ViewModel(){


    suspend fun getCatalog() {
        //networkCommunication.map(NetworkResult.Loading())
        val result = repository.getCatalog()
       // networkCommunication.map(result)
    }
}


