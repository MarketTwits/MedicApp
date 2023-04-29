package com.example.medicapp

import android.app.Application
import com.example.medicapp.data.CloudDataSource
import com.example.medicapp.data.net.retrofit.RetrofitBuilder
import com.example.medicapp.domain.RepositoryImpl
import com.example.medicapp.domain.SharedPrefDataSource
import com.example.medicapp.presentation.auth.send_code.AuthViewModel
import com.example.medicapp.presentation.auth.sign_in.SignInViewModel
import com.example.medicapp.presentation.main.MainViewModel
import com.google.gson.Gson

class MedicApp : Application() {
    lateinit var authViewModel : AuthViewModel
    lateinit var mainViewModel: MainViewModel
    lateinit var signInViewModel : SignInViewModel
    private val apiService = RetrofitBuilder.apiService

    override fun onCreate() {
        super.onCreate()

        mainViewModel = MainViewModel(
            RepositoryImpl(CloudDataSource.Base(apiService, Gson())),
        )
        signInViewModel = SignInViewModel(
            RepositoryImpl(CloudDataSource.Base(apiService, Gson())),
        )
        authViewModel = AuthViewModel(
            RepositoryImpl(CloudDataSource.Base(apiService, Gson())),
            SharedPrefDataSource.Base(this)
        )
    }
}