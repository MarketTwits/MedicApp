package com.example.medicapp.presentation.auth.send_code

interface HandleAuthCodeActivity {
    fun handleSuccess(token : String)
    fun handleLoading()
    fun handleFailed(error : String)
    fun initView() = Unit
    fun handleLoadingAndError(loading: Boolean)
    fun observeViewModel()
}