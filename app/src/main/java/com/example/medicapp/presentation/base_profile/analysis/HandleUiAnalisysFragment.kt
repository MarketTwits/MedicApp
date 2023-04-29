package com.example.medicapp.presentation.base_profile.analysis

interface HandleUiAnalisysFragment {
    fun handleSuccess()
    fun handleLoading()
    fun handleFailed(error : String)
    fun initView() = Unit
    fun handleLoadingAndError(loading: Boolean, buttonState: Boolean)
    fun observeViewModel()
}