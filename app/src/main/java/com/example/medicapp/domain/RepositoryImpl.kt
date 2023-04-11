package com.example.medicapp.domain

import com.example.medicapp.data.CloudDataSource
import com.example.medicapp.data.NetworkResult

class RepositoryImpl(
    private val cloudDataSource: CloudDataSource
    ) : Repository {
    override suspend fun getCatalog(): NetworkResult {
       return cloudDataSource.getData()
    }
}