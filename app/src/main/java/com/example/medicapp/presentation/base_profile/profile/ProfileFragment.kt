package com.example.medicapp.presentation.base_profile.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.medicapp.MedicApp
import com.example.medicapp.R
import com.example.medicapp.databinding.FragmentProfileBinding
import com.example.medicapp.presentation.auth.sign_in.SignInActivity


class ProfileFragment : Fragment() {
   lateinit var binding : FragmentProfileBinding
   lateinit var viewModel: ProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        // Inflate the layout for this fragment
        viewModel = (requireActivity().application as MedicApp).profileViewModel
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exitFromApp()
    }
    private fun exitFromApp(){
        binding.btExit.setOnClickListener {
            viewModel.cleanToken()
            requireActivity().finish()
            startActivity(Intent(requireActivity(),SignInActivity::class.java))
        }
    }
}