package com.example.medicapp.presentation.main

import androidx.lifecycle.*
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.data.data_model.CatalogCloud
import com.example.medicapp.data.data_model.CatalogCloudItem
import com.example.medicapp.domain.RepositoryImpl
import com.example.medicapp.presentation.BaseObserve
import com.example.medicapp.presentation.Communication


class MainViewModel(
    private val repository: RepositoryImpl,
    private val networkCommunication: NetworkCommunication,
) : ViewModel(), BaseObserve<NetworkResult<CatalogCloud>> {

    override fun observe(
        owner: LifecycleOwner,
        observer: Observer<NetworkResult<CatalogCloud>>,
    ) {
        networkCommunication.observe(owner, observer)
    }

    suspend fun getCatalog() {
        networkCommunication.map(NetworkResult.Loading())
        val result = repository.getCatalog()
        networkCommunication.map(result)
    }
}
interface NetworkCommunication : Communication<NetworkResult<CatalogCloud>> {
    class Base() : Communication.Abstract<NetworkResult<CatalogCloud>>(), NetworkCommunication
}


