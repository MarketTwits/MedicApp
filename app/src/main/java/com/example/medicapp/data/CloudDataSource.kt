package com.example.medicapp.data

import com.example.medicapp.data.data_model.CatalogCloudItem
import com.example.medicapp.data.retrofit.ApiService

interface CloudDataSource : DataSource {

    class Base(private val apiService: ApiService) : CloudDataSource{

        override suspend fun getData() : NetworkResult {
            return try{
                NetworkResult.Success(apiService.getCatalog())
            }catch (e : Exception){
                NetworkResult.Failed(e.message ?: "exception")
            }
        }
    }
}
interface DataSource{
    suspend fun getData() : NetworkResult
}