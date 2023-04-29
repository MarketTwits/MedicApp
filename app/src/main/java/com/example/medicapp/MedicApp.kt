package com.example.medicapp

import android.app.Application
import com.example.medicapp.data.CloudDataSource
import com.example.medicapp.data.net.retrofit.RetrofitBuilder
import com.example.medicapp.domain.RepositoryImpl
import com.example.medicapp.domain.SharedPrefDataSource
import com.example.medicapp.presentation.auth.send_code.AuthViewModel
import com.example.medicapp.presentation.auth.sign_in.SignInViewModel
import com.example.medicapp.presentation.base_profile.analysis.AnalysisViewModel
import com.example.medicapp.presentation.base_profile.profile.ProfileViewModel
import com.example.medicapp.presentation.main.MainViewModel
import com.google.gson.Gson

class MedicApp : Application() {
    lateinit var authViewModel: AuthViewModel
    lateinit var mainViewModel: MainViewModel
    lateinit var signInViewModel: SignInViewModel
    lateinit var analisysViewModel: AnalysisViewModel
    lateinit var profileViewModel : ProfileViewModel
    private val apiService = RetrofitBuilder.apiService

    override fun onCreate() {
        super.onCreate()

        mainViewModel = MainViewModel(
            RepositoryImpl(CloudDataSource.Base(apiService, Gson())),
            SharedPrefDataSource.Base(this)
        )
        signInViewModel = SignInViewModel(
            RepositoryImpl(CloudDataSource.Base(apiService, Gson())),
        )
        authViewModel = AuthViewModel(
            RepositoryImpl(CloudDataSource.Base(apiService, Gson())),
            SharedPrefDataSource.Base(this)
        )
        analisysViewModel = AnalysisViewModel(
            RepositoryImpl(CloudDataSource.Base(apiService, Gson()))
        )
        profileViewModel = ProfileViewModel(
            SharedPrefDataSource.Base(this)
        )
    }
}