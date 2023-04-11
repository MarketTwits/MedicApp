package com.example.medicapp.data.retrofit

import com.example.medicapp.data.data_model.CatalogCloudItem
import retrofit2.http.GET

interface ApiService {
    @GET("catalog")
    suspend fun getCatalog() : List<CatalogCloudItem>
}