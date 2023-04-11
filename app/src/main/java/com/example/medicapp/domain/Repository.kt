package com.example.medicapp.domain

import com.example.medicapp.data.NetworkResult

interface Repository {
    suspend fun getCatalog() : NetworkResult
}