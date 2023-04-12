package com.example.medicapp.domain

import com.example.medicapp.data.CloudDataSource
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.data.data_model.CatalogCloud
import com.example.medicapp.data.data_model.CatalogCloudItem
import okhttp3.ResponseBody

class RepositoryImpl(
    private val cloudDataSource: CloudDataSource
    ) : Repository {
    override suspend fun getCatalog(): NetworkResult<CatalogCloud> {
       return cloudDataSource.getCatalog()
    }
    override suspend fun sendEmail(email: String): NetworkResult<ResponseBody> {
        return cloudDataSource.sendAuthCode(email)
    }
}