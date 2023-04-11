package com.example.medicapp.presentation

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatchersWrapper {
    fun io() : CoroutineDispatcher
    fun ui() : CoroutineDispatcher

    class Base() : DispatchersWrapper{
        override fun io(): CoroutineDispatcher = Dispatchers.IO
        override fun ui(): CoroutineDispatcher = Dispatchers.Main
    }
}