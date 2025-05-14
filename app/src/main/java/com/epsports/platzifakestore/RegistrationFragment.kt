package com.epsports.platzifakestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.epsports.platzifakestore.databinding.FragmentRegistrationBinding


class RegistrationFragment : Fragment() {
lateinit var binding: FragmentRegistrationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)





        return binding.root
    }


}