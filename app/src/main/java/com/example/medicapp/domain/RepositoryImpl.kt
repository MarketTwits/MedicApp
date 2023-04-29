package com.example.medicapp.domain

import com.example.medicapp.data.CloudDataSource
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.data.net.models.CatalogCloudItem
import com.example.medicapp.data.net.models.SendCodeResponseCloud
import com.example.medicapp.data.net.models.SignInResponseCloud

class RepositoryImpl(
    private val cloudDataSource: CloudDataSource
    ) : Repository {
    override suspend fun getCatalog(): NetworkResult<List<CatalogCloudItem>> {
       return cloudDataSource.getCatalog()
    }
    override suspend fun sendEmail(email: String): NetworkResult<SendCodeResponseCloud> {
        return cloudDataSource.sendAuthCode(email)
    }
    override suspend fun signIn(
        email: String,
        authCode: String,
    ): NetworkResult<SignInResponseCloud> {
        return cloudDataSource.signIn(email, authCode)
    }
}