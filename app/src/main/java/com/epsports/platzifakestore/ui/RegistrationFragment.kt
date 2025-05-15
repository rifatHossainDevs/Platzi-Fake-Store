package com.epsports.platzifakestore.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.epsports.platzifakestore.Extension.value
import com.epsports.platzifakestore.R
import com.epsports.platzifakestore.databinding.FragmentRegistrationBinding
import com.epsports.platzifakestore.model.RequestRegister
import com.epsports.platzifakestore.viewModel.AuthViewModel


class RegistrationFragment : Fragment() {
    lateinit var binding: FragmentRegistrationBinding
    private val viewModel: AuthViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        allClickListener()

        observerListener()

        return binding.root
    }

    private fun allClickListener() {
        binding.btnRegister.setOnClickListener {
            val name = binding.etName.value()
            val email = binding.etEmail.value()
            val password = binding.etPassword.value()
            val avatar = binding.etAvatar.value()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && avatar.isNotEmpty()) {
                val requestRegister = RequestRegister(name, email, password, avatar)
                viewModel.authRegister(requestRegister)
            } else {
                Toast.makeText(
                    requireContext(),
                    "All Field need to be filled",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }
    }

    private fun observerListener() {
        viewModel.register.observe(viewLifecycleOwner) { registerUser ->
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
            Toast.makeText(requireContext(), "Registration Successful", Toast.LENGTH_SHORT).show()
        }

        viewModel.registerError.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }

}