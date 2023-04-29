package com.example.medicapp.presentation.auth.sign_in

interface HandleUiSignInActivity {
    fun handleSuccess()
    fun handleLoading()
    fun handleFailed(error : String)
    fun initView() = Unit
    fun handleLoadingAndError(loading: Boolean, buttonState: Boolean)
    fun observeViewModel()
}