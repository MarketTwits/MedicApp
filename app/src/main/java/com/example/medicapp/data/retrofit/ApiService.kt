package com.example.medicapp.data.retrofit

import com.example.medicapp.data.data_model.CatalogCloud
import com.example.medicapp.data.data_model.CatalogCloudItem
import com.example.medicapp.data.data_model.SendCodeResponseCloud
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @GET("catalog")
    suspend fun getCatalog() : CatalogCloud

    @POST("sendCode")
    suspend fun sendAuthCode(
        @Header("email") email : String
    ) : SendCodeResponseCloud
}