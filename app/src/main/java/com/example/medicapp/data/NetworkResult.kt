package com.example.medicapp.data

sealed class NetworkResult{
    class Success<T>(val data : T) : NetworkResult()
    class Failed(val errorMessage : String) : NetworkResult()
    object Loading : NetworkResult()
}
