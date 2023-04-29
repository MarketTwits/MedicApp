package com.example.medicapp.domain

import com.example.medicapp.data.NetworkResult
import com.example.medicapp.data.net.models.CatalogCloudItem
import com.example.medicapp.data.net.models.NewsCloudItem
import com.example.medicapp.data.net.models.SendCodeResponseCloud
import com.example.medicapp.data.net.models.SignInResponseCloud


interface Repository {
    suspend fun getCatalog() : NetworkResult<List<CatalogCloudItem>>
    suspend fun getNews() : NetworkResult<List<NewsCloudItem>>
    suspend fun sendEmail(email : String) : NetworkResult<SendCodeResponseCloud>
    suspend fun signIn(email : String, authCode : String) : NetworkResult<SignInResponseCloud>
}