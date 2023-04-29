package com.example.medicapp.presentation.base_profile.analysis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.medicapp.R
import com.example.medicapp.databinding.FragmentAnalyzesBinding


class AnalysisFragment : Fragment() {

    lateinit var binding : FragmentAnalyzesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        binding = FragmentAnalyzesBinding.inflate(inflater, container, false)
        return binding.root
    }


}