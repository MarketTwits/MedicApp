package com.example.medicapp.presentation.base_profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.medicapp.R

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
    }
}