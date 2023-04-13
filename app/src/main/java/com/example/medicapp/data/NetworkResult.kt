package com.example.medicapp.data

sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : NetworkResult<T>(data){
        override fun toString(): String {
            return "Success $data"
        }
    }
    class Error<T>(errorMessage: String?, data: T? = null) : NetworkResult<T>(data, errorMessage){
        override fun toString(): String {
            return "Error $data"
        }
    }
    class Loading<T> : NetworkResult<T>(){
        override fun toString(): String {
            return "Loading..."
        }
    }
}
