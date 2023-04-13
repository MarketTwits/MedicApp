package com.example.medicapp.data

import com.example.medicapp.data.data_model.CatalogCloud
import com.example.medicapp.data.data_model.CatalogCloudItem
import com.example.medicapp.data.data_model.SendCodeResponseCloud
import com.example.medicapp.data.retrofit.ApiService
import com.google.gson.Gson
import okhttp3.HttpUrl
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.UnknownHostException

interface CloudDataSource {
    suspend fun getCatalog(): NetworkResult<CatalogCloud>
    suspend fun sendAuthCode(email: String): NetworkResult<SendCodeResponseCloud>

    class Base(
        private val apiService: ApiService,
        private val gson: Gson,
    ) : CloudDataSource {
        override suspend fun getCatalog(): NetworkResult<CatalogCloud> {
            return try {
                NetworkResult.Success(apiService.getCatalog())
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "unknown exception")
            }
        }

        override suspend fun sendAuthCode(email: String): NetworkResult<SendCodeResponseCloud> {
            return try {
                val request = apiService.sendAuthCode(email)
                NetworkResult.Success(request)
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        val error = gson.fromJson(
                            e.response()?.errorBody()?.string(),
                            SendCodeResponseCloud::class.java
                        )
                        val errorMessage = error.errors[0]
                        NetworkResult.Error(errorMessage)
                    }
                    else -> NetworkResult.Error(e.message)
                }
            }
        }
    }
}
