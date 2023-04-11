package com.example.medicapp.presentation.main

import androidx.lifecycle.*
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.domain.Repository
import com.example.medicapp.domain.RepositoryImpl
import com.example.medicapp.presentation.BaseObserve
import com.example.medicapp.presentation.NetworkCommunication

class MainViewModel(
    private val repository: RepositoryImpl,
    private val networkCommunication: NetworkCommunication
    ) : ViewModel(), BaseObserve<NetworkResult> {
    
    override fun observe(owner: LifecycleOwner, observer: Observer<NetworkResult>) {
        networkCommunication.observe(owner, observer)
    }
    suspend fun getCatalog() {
            val result = repository.getCatalog()
            networkCommunication.map(result)
    }
}

