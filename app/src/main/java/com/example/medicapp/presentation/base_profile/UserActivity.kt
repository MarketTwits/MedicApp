package com.example.medicapp.presentation.base_profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.medicapp.R
import com.example.medicapp.presentation.base_profile.analysis.AnalysisFragment
import com.example.medicapp.presentation.base_profile.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.analyzes -> {
                    loadFragment(AnalysisFragment())
                    true
                }
                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> {
                    Toast.makeText(this, "This screen don't steal create", Toast.LENGTH_LONG).show()
                    true
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction
            .replace(R.id.fragmentContainerUser, fragment)
            .addToBackStack(null)
            .commit()
    }
}