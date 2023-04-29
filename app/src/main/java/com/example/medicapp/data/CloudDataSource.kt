package com.example.medicapp.data



import com.example.medicapp.data.net.models.CatalogCloudItem
import com.example.medicapp.data.net.models.NewsCloudItem
import com.example.medicapp.data.net.models.SendCodeResponseCloud
import com.example.medicapp.data.net.models.SignInResponseCloud
import com.example.medicapp.data.net.retrofit.ApiService
import com.google.gson.Gson
import retrofit2.HttpException

interface CloudDataSource {
    suspend fun getCatalog(): NetworkResult<List<CatalogCloudItem>>
    suspend fun getNews() : NetworkResult<List<NewsCloudItem>>
    suspend fun sendAuthCode(email: String): NetworkResult<SendCodeResponseCloud>
    suspend fun signIn(email: String, authCode : String) : NetworkResult<SignInResponseCloud>

    class Base(
        private val apiService: ApiService,
        private val gson: Gson,
    ) : CloudDataSource {
        override suspend fun getCatalog(): NetworkResult<List<CatalogCloudItem>> {
            return try {
                NetworkResult.Success(apiService.getCatalog())
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "unknown exception")
            }
        }

        override suspend fun getNews(): NetworkResult<List<NewsCloudItem>> {
            return try {
                NetworkResult.Success(apiService.getNews())
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
                        NetworkResult.Error(error.errors)
                    }
                    else -> NetworkResult.Error(e.message)
                }
            }
        }
        override suspend fun signIn(email: String, authCode : String) : NetworkResult<SignInResponseCloud>{
            return try {
                val request = apiService.signIn(email, authCode)
                NetworkResult.Success(request)
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        val error = gson.fromJson(
                            e.response()?.errorBody()?.string(),
                            SendCodeResponseCloud::class.java
                        )
                        NetworkResult.Error(error.errors)
                    }
                    else -> NetworkResult.Error(e.message)
                }
            }
        }
    }
}
