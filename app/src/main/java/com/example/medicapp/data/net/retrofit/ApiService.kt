package com.example.medicapp.data.net.retrofit


import com.example.medicapp.data.net.models.CatalogCloudItem
import com.example.medicapp.data.net.models.NewsCloudItem
import com.example.medicapp.data.net.models.SendCodeResponseCloud
import com.example.medicapp.data.net.models.SignInResponseCloud
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @GET("catalog")
    suspend fun getCatalog() : List<CatalogCloudItem>

    @GET("news")
    suspend fun getNews() : List<NewsCloudItem>

    @POST("sendCode")
    suspend fun sendAuthCode(
        @Header("email") email : String
    ) : SendCodeResponseCloud

    @POST("signin")
    suspend fun signIn(
        @Header("email") email : String,
        @Header("code") authCode : String
    ) : SignInResponseCloud
}