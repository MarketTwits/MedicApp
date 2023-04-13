package com.example.medicapp.domain

import com.example.medicapp.data.NetworkResult
import com.example.medicapp.data.data_model.CatalogCloud
import com.example.medicapp.data.data_model.SendCodeResponseCloud
import okhttp3.ResponseBody


interface Repository {
    suspend fun getCatalog() : NetworkResult<CatalogCloud>
    suspend fun sendEmail(email : String) : NetworkResult<SendCodeResponseCloud>
}