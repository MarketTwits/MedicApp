package com.example.medicapp.data

import com.example.medicapp.data.data_model.CatalogCloud
import com.example.medicapp.data.data_model.CatalogCloudItem
import com.example.medicapp.data.retrofit.ApiService
import okhttp3.ResponseBody

interface CloudDataSource {
    suspend fun getCatalog(): NetworkResult<CatalogCloud>
    suspend fun sendAuthCode(email : String) : NetworkResult<ResponseBody>

    class Base(private val apiService: ApiService) : CloudDataSource {
        override suspend fun getCatalog(): NetworkResult<CatalogCloud>  {
            return try {
                NetworkResult.Success(apiService.getCatalog())
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "unknown exception")
            }
        }
        override suspend fun sendAuthCode(email: String): NetworkResult<ResponseBody> {
            return try {
                NetworkResult.Success(apiService.sendAuthCode(email))
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "unknown exception")
            }
        }
    }
}
