package com.example.medicapp.domain

import com.example.medicapp.data.CloudDataSource
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.data.data_model.AuthTokenResponse
import com.example.medicapp.data.data_model.CatalogCloud
import com.example.medicapp.data.data_model.CatalogCloudItem
import com.example.medicapp.data.data_model.SendCodeResponseCloud
import okhttp3.ResponseBody

class RepositoryImpl(
    private val cloudDataSource: CloudDataSource
    ) : Repository {
    override suspend fun getCatalog(): NetworkResult<CatalogCloud> {
       return cloudDataSource.getCatalog()
    }
    override suspend fun sendEmail(email: String): NetworkResult<SendCodeResponseCloud> {
        return cloudDataSource.sendAuthCode(email)
    }

    override suspend fun signIn(email: String, authCode: String): NetworkResult<AuthTokenResponse> {
       return cloudDataSource.signIn(email, authCode)
    }
}