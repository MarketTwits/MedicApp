package com.example.medicapp

import android.app.Application
import com.example.medicapp.data.CloudDataSource
import com.example.medicapp.data.retrofit.RetrofitBuilder
import com.example.medicapp.domain.RepositoryImpl
import com.example.medicapp.presentation.AuthCommunication
import com.example.medicapp.presentation.NetworkCommunication
import com.example.medicapp.presentation.auth.AuthViewModel
import com.example.medicapp.presentation.main.MainViewModel

class MedicApp : Application() {

    lateinit var mainViewModel: MainViewModel
    lateinit var authViewModel : AuthViewModel
    private val apiService = RetrofitBuilder.apiService

    override fun onCreate() {
        super.onCreate()

        mainViewModel = MainViewModel(
            RepositoryImpl(CloudDataSource.Base(apiService)),
            NetworkCommunication.Base()
        )
        authViewModel = AuthViewModel(
            RepositoryImpl(CloudDataSource.Base(apiService)),
            AuthCommunication.Base()
        )
    }
}