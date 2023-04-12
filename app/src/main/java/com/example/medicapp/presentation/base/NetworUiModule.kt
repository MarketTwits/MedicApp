package com.example.medicapp.presentation.base

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.medicapp.data.NetworkResult
import kotlinx.coroutines.launch

interface NetworkUiModule {
     fun getData()
     fun observe()
}