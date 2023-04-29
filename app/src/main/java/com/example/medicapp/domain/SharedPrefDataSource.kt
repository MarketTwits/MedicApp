package com.example.medicapp.domain

import android.content.Context
import android.content.SharedPreferences

interface SharedPrefDataSource {
    fun saveAuthToken(token : String)
    fun getAuthToken() : String

    class Base(context : Context) : SharedPrefDataSource{

        private val sharedPrefs: SharedPreferences =
            context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

        override fun saveAuthToken(token: String) {
            sharedPrefs.edit().putString(SharedPreferencesKeys.AuthorizationToken.name, "Bearer $token").apply()
        }

        override fun getAuthToken(): String {
            return sharedPrefs.getString(SharedPreferencesKeys.AuthorizationToken.name, null) ?: ""
        }
    }
}