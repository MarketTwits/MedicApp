package com.example.medicapp

import android.app.Application
import com.example.medicapp.data.CloudDataSource
import com.example.medicapp.data.retrofit.RetrofitBuilder
import com.example.medicapp.domain.RepositoryImpl
import com.example.medicapp.presentation.auth.AuthCommunication
import com.example.medicapp.presentation.auth.AuthViewModel
import com.example.medicapp.presentation.send_code.SendCodeCommunication

import com.example.medicapp.presentation.send_code.SendCodeViewModel
import com.example.medicapp.presentation.main.MainViewModel
import com.example.medicapp.presentation.main.NetworkCommunication
import com.google.gson.Gson

class MedicApp : Application() {

    lateinit var mainViewModel: MainViewModel
    lateinit var sendCodeViewModel : SendCodeViewModel
    lateinit var authViewModel : AuthViewModel
    private val apiService = RetrofitBuilder.apiService

    override fun onCreate() {
        super.onCreate()

        mainViewModel = MainViewModel(
            RepositoryImpl(CloudDataSource.Base(apiService, Gson())),
            NetworkCommunication.Base()
        )
        sendCodeViewModel = SendCodeViewModel(
            RepositoryImpl(CloudDataSource.Base(apiService, Gson())),
            SendCodeCommunication.Base()
        )
        authViewModel = AuthViewModel(
            RepositoryImpl(CloudDataSource.Base(apiService, Gson())),
            AuthCommunication.Base()
        )
    }
}